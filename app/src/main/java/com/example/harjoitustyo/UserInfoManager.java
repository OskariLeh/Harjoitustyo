package com.example.harjoitustyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView profileName;
    TextView tvErrors;

    EditText etN;
    EditText etPw;
    EditText etE;
    Context context = null;
    Document xmlDoc;
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = ProfileActivity.this;
        etN = findViewById(R.id.etName);
        etPw = findViewById(R.id.etPasswd);
        etE = findViewById(R.id.etEmail);
        tvErrors = findViewById(R.id.tvError);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        return true;
    }
    // Handles the actions of toolbar buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_profile:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addUser(View v) {

        String name = etN.getText().toString();
        String pw = etPw.getText().toString();
        String email = etE.getText().toString();
        Boolean newUser = true;
        for (User user : users) {
            if (user.getName().toString().contentEquals(name)){
                System.out.println("Käyttäjä "+user.getName().toString()+ " on jo olemassa");
                tvErrors.setText("User "+user.getName().toString()+ " already exists");
                newUser = false;
                break;
            }
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
                tvErrors.setText("User "+ name +" created!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void loginCheck(View v) {
        String name = etN.getText().toString();
        String pw = etPw.getText().toString();
        //System.out.println(context.getFilesDir());
        for (User user : users) {
            String qname = user.getName().toString();
            String qpass = user.getPasscode().toString();
            if (name.contentEquals(qname)) {
                if (pw.contentEquals(qpass)) {
                    System.out.println("Login ok!");
                    tvErrors.setText("Login ok!");
                    break;
                } else {
                    System.out.println("Wrong pw");
                    tvErrors.setText("Wrong password");
                    break;
                }
            }
        }
    }
}
