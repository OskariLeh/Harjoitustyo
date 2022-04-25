package com.example.harjoitustyo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class JSONReader {

    public void readJSON(){
        String json = getJSON();
        System.out.println(json);
    }

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