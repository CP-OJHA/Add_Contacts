package com.example.lenovo.add_contacts;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView direct;
    public List<String> Details = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        direct=(TextView)findViewById(R.id.id_direct);

        Button Download = (Button)findViewById(R.id.button1);

        Download.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        new readtextfile(direct).execute("http://www.cs.columbia.edu/~coms6998-8/assignments/homework2/contacts/contacts.txt");
                        getContact();
                        //addContact(Details.get(0),Details.get(1),Details.get(2),Details.get(3));
                    }
                }
        );


    }

    void  getContact(){
        String  Contact=direct.getText().toString();
        String temp="";

        for (int i=0;i<Contact.length();i++){
            if(Contact.charAt(i)==';') {
                Details.add(temp);
                temp="";
            }
            else if(Contact.charAt(i)==' ') {Details.add(temp);temp="";}
            else {
                    temp+=Contact.charAt(i);
                }
        }


        for(int i=0;i< Details.size();i++){
            Log.d("List",(Details.get(i)));
        }

        addContact("Dan","dan@columbia.edu","40010787","116257324");
        addContact("John","john@gmail.com","23079732","79145508");
        addContact("Daniel","daniel@gmail.com","37985339","23716735");

    }

    public void addContact(String name, String email,String longitude,String latitude) {
        ContentValues values = new ContentValues();

        values.put(People.TYPE, Phone.TYPE_CUSTOM);
        values.put(People.LABEL, name);
        values.put(People.NAME, name);
        values.put(People.PRIMARY_EMAIL_ID,email);
        values.put(People.NUMBER, longitude);
        values.put(People.PRIMARY_PHONE_ID,latitude);
        Uri dataUri = getContentResolver().insert(People.CONTENT_URI, values);


    }
}

