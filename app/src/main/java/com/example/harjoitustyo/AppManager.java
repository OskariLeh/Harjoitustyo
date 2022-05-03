package com.example.harjoitustyo;

import android.content.Context;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AppManager {
    private List<Lake> lakes = new ArrayList<Lake>();
    private List<Trip> trips = new ArrayList<Trip>();
    private List<Lake> favorites = new ArrayList<>();
    Context context = null;


    public List<Lake> getLakes() {
        return lakes;
    }


    public void readJSON() {
        if (lakes.isEmpty()) {
            String json = getJSON();

            if (json != null) {
                try {
                    JSONObject jObj = new JSONObject(json);
                    JSONArray jsonArray = jObj.getJSONArray("value");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Lake lake = new Lake();
                        lake.setId(Integer.parseInt(jsonObject.getString("Jarvi_Id")));
                        lake.setName(jsonObject.getString("Nimi"));
                        lake.setTown(jsonObject.getString("KuntaNimi"));
                        if (jsonObject.getString("Vesiala") != "null") {
                            lake.setAreaOfLake(Double.parseDouble(jsonObject.getString("Vesiala")));
                        }
                        lake.setCordinates(jsonObject.getString("KoordErLat"), jsonObject.getString("KoordErLong"));
                        if (jsonObject.getString("SyvyysKeski") != "null") {
                            lake.setAverageDepth(Double.parseDouble(jsonObject.getString("SyvyysKeski")));
                        }
                        lake.setDrainageBasin(jsonObject.getString("VesalNimi"));
                        lakes.add(lake);
                    }
                    URL newUrl = new URL(jObj.getString("odata.nextLink"));
                    //readJSON(newUrl);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readJSON(URL url) {
        String json = getJSON(url);

        if (json != null) {
            try {
                JSONObject jObj = new JSONObject(json);
                JSONArray jsonArray = jObj.getJSONArray("value");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Lake lake = new Lake();
                    lake.setId(Integer.parseInt(jsonObject.getString("Jarvi_Id")));
                    lake.setName(jsonObject.getString("Nimi"));
                    lake.setTown(jsonObject.getString("KuntaNimi"));
                    if (jsonObject.getString("Vesiala") != "null") {
                        lake.setAreaOfLake(Double.parseDouble(jsonObject.getString("Vesiala")));
                    }
                    lake.setCordinates(jsonObject.getString("KoordErLat"), jsonObject.getString("KoordErLong"));
                    if (jsonObject.getString("SyvyysKeski") != "null") {
                        lake.setAverageDepth(Double.parseDouble(jsonObject.getString("SyvyysKeski")));
                    }
                    lake.setDrainageBasin(jsonObject.getString("VesalNimi"));
                    lakes.add(lake);
                }
                if (jObj.has("odata.nextLink")) {
                    URL newUrl = new URL(jObj.getString("odata.nextLink"));
                    readJSON(newUrl);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
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

    public String getJSON(URL url) {
        String response = null;
        try {
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

    public void getTrips(User user) {
        InputStream ins = null;
        Document xmlDoc;
        String username = user.getName().toString();
        String fname = username + ".xml";

        try {
            ins = context.openFileInput(fname);
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docB.parse(ins);
            NodeList nList = xmlDoc.getElementsByTagName("trip");
            for (Integer i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Element element = (Element) node;
                String lake = element.getElementsByTagName("lake").item(0).getTextContent();
                String town = element.getElementsByTagName("town").item(0).getTextContent();
                String description = element.getElementsByTagName("desc").item(0).getTextContent();
                String time = element.getElementsByTagName("time").item(0).getTextContent();

                Trip trip = new Trip();
                trip.setDescription(description);
                //trip.setTime(time); //TODO time muunnos?
                //trip.setLake(lake); //TODO laitetaanko reissuun kaikki järven tiedot vai haetaanko tässä esim id:n perusteella oikea ja linkitetään se tähän?
                trip.setDuration("kesto"); //TODO mistä tää tulee?

                trips.add(trip);

            }

            nList = xmlDoc.getElementsByTagName("favorite");
            for (Integer i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Element element = (Element) node;
                String lake = element.getElementsByTagName("lake").item(0).getTextContent();

                favorites.add(lake); //TODO miten tää?
            }
            //System.out.println("Listassa käyttäjiä: " + Integer.toString(i));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void saveTrips(User user) {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        String username = user.getName();
        String fname = username + ".xml";
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("","favorites");
            for (Lake lake : favorites){
                serializer.startTag("","favorite");

                serializer.startTag("","name");
                serializer.text();
                serializer.endTag("","name");

                //TODO ynnä muut tiedot
                serializer.endTag("","favorite");
            }
            serializer.endTag("",favorites);
            serializer.startTag("", "trips"); //TODO näille oikeat arvot

            for (Trip trip : trips) {
                serializer.startTag("", "trip");

                serializer.startTag("", "name");
                serializer.text("");
                serializer.endTag("", "name");

                serializer.startTag("", "password");
                serializer.text("");
                serializer.endTag("", "password");

                serializer.startTag("", "email");
                serializer.text("");
                serializer.endTag("", "email");

                serializer.endTag("", "trip");

            }
            serializer.endTag("", "trips");
            serializer.endDocument();
            String result = writer.toString();
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fname, Context.MODE_PRIVATE));
            osw.write(result);
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
