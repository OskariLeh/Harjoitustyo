package com.example.harjoitustyo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class AppManager {
    private ArrayList<Lake> lakes = new ArrayList<Lake>();
    private ArrayList<String> lakeNames = new ArrayList<>();

    public ArrayList<String> getLakeNames() {
        return lakeNames;
    }

    public void readJSON(){
        String json = getJSON();
        System.out.println(json);

        if (json != null) {
            try {
                JSONObject jObj = new JSONObject(json);
                JSONArray jsonArray = jObj.getJSONArray("value");

                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Lake lake = new Lake();
                    lake.setId(Integer.parseInt(jsonObject.getString("Jarvi_Id")));
                    lake.setName(jsonObject.getString("Nimi"));
                    lakeNames.add(jsonObject.getString("Nimi"));
                    lake.setTown(jsonObject.getString("KuntaNimi"));
                    lake.setAreaOfLake(Double.parseDouble(jsonObject.getString("Vesiala")));
                    lake.setCordinates(jsonObject.getString("KoordErLat"), jsonObject.getString("KoordErLong"));
                    if (jsonObject.getString("SyvyysKeski") != "null") {
                        lake.setAverageDepth(Double.parseDouble(jsonObject.getString("SyvyysKeski")));
                    }
                    lake.setDrainageBasin(jsonObject.getString("VesalNimi"));
                    lakes.add(lake);
                }
                Collections.sort(lakeNames);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }


    // Gets JSON file returns it as text
    public String getJSON() {
        String response = null;
        try {
            URL url = new URL("http://rajapinnat.ymparisto.fi/api/jarvirajapinta/1.0/odata/Jarvi");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream input = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
            response = builder.toString();
            input.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
