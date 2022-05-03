

package com.example.harjoitustyo;


import android.content.Context;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class UserManager implements Serializable {
    private boolean loggedIn = false;
    static Context context;
    static Document xmlDoc;
    static ArrayList<User> users = new ArrayList<User>();
    static User user;

    public UserManager(Context context){
        this.context = context;


        InputStream ins = null;
        try {
            ins = context.openFileInput("userdata.xml");
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docB.parse(ins);
            NodeList nList = xmlDoc.getElementsByTagName("user");
            Integer i;
            for(i=0;i<nList.getLength();i++) {
                Node node = nList.item(i);
                Element element = (Element) node;
                String username = element.getElementsByTagName("name").item(0).getTextContent();
                String password = element.getElementsByTagName("password").item(0).getTextContent();
                String email = element.getElementsByTagName("email").item(0).getTextContent();

                User user = new User();
                user.setName(username);
                user.setPasscode(password);
                user.setEmail(email);
                users.add(user);
            }
            System.out.println("Listassa käyttäjiä: " + Integer.toString(i));
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


    public String addUser(String name, String pw, String email) {
        String error = "";
        System.out.println(context.getFilesDir());
        Boolean newUser = true;
        for (User user : users) {
            if (user.getName().toString().contentEquals(name)){
                System.out.println("Käyttäjä "+user.getName().toString()+ " on jo olemassa");
                error = ("User "+user.getName().toString()+ " already exists");
                newUser = false;
            }
        }
        if (pw.isEmpty()){
            error = "Add password";
            newUser = false;
        }
        if (email.isEmpty()){
            error = "Add email";
            newUser = false;
        }

        if (newUser) {
            User user = new User();
            user.setName(name);
            user.setPasscode(pw);
            user.setEmail(email);
            users.add(user);

            XmlSerializer serializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            try{
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8",true);
                serializer.startTag("","users");

                for (User usern : users) {
                    serializer.startTag("","user");

                    serializer.startTag("","name");
                    serializer.text(usern.getName().toString());
                    serializer.endTag("","name");

                    serializer.startTag("","password");
                    serializer.text(usern.getPasscode().toString());
                    serializer.endTag("","password");

                    serializer.startTag("","email");
                    serializer.text(usern.getEmail().toString());
                    serializer.endTag("","email");

                    serializer.endTag("","user");

                }
                serializer.endTag("","users");
                serializer.endDocument();
                String result = writer.toString();
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("userdata.xml",Context.MODE_PRIVATE));
                osw.write(result);
                osw.close();

                String fname = name + ".xml";
                osw = new OutputStreamWriter(context.openFileOutput(fname,Context.MODE_PRIVATE));
                osw.write("");
                osw.close();
                error = ("User "+ name +" created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return error;
    }

    public String loginCheck(String name, String pw) {
        String error = "";
        for (User user : users) {
            String qname = user.getName();
            String qpass = user.getPasscode();
            String qmail = user.getEmail();
            if (name.contentEquals(qname)) {
                if (pw.contentEquals(qpass)) {
                    System.out.println("Login ok!");
                    this.user = user;
                    error = ("Login ok!");
                    loggedIn = true;
                    break;
                } else {
                    System.out.println("Wrong pw");
                    error = ("Wrong password");
                }
            }
            else if (name.contentEquals(qmail)){
                if (pw.contentEquals(qpass)) {
                    System.out.println("Login ok!");
                    this.user = user;
                    error = ("Login ok!");
                    loggedIn = true;
                    break;
                } else {
                    System.out.println("Wrong pw");
                    error = ("Wrong password");
                }
            }
            else {
                error = "No such user found";
            }
        }
        return error;
    }

    public boolean getLoggedIn() {return loggedIn;}
}


