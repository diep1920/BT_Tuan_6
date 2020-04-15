package com.example.gmail_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MailModel> mails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mails = new ArrayList<>();
        mails.add(new MailModel("Edurila.com", "$19 Only (First 10 spots)", "Are you looking to Learn Web", "12:34PM"));
        mails.add(new MailModel("Chris Abad", "Help make campaign monitor better", "Let us know your thought", "11:22AM"));
        mails.add(new MailModel("Tuto.com", "8h de formation gratuite", "Photoshop, SEO, Blender", "11:04AM"));
        mails.add(new MailModel("Support", "Societe Ovh", "SAS OVH", "10:26AM"));
        mails.add(new MailModel("Matt from ionic", "The New Ionic Creator Is Here!", "Announcing all-new Creator", "12:34PM"));
        mails.add(new MailModel("Microsoft", "Welcome to IT student training", "Click this link to proceed", "9:24PM"));
        mails.add(new MailModel("Pro. Ng", "Assignment Due!", "You was late for deadline", "03:34AM"));

        MailAdapter mailAdapter = new MailAdapter(mails);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(mailAdapter);


    }
}
