package com.example.harjoitustyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Time;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TripLogger {

    private Context context;

    public TripLogger(Context context) {
        this.context = context;
    }


    //Write the trip to the xml file
    public void saveTrip(){
        OutputStreamWriter outputStreamWriter = null;

        String x = "";

        try {
            InputStream inputStream = context.openFileInput("x");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);



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

    public String showTrips(){
        try {
            InputStream inputStream = context.openFileInput("x");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            //String lake = document.getElementsByTagName();
            //String time = document.getElementsByTagName();
            //String description = document.getElementsByTagName();

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return "";
    }
}
