package com.example.vacctrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Third extends AppCompatActivity {

    Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Getting instance of edittext and button
        button1 = findViewById(R.id.cowinbutton);
        button2=findViewById(R.id.hmbutton);
        button3=findViewById(R.id.childbutton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg)
            {
                String phone_number = "1123978046";

                Intent phone_intent = new Intent(Intent.ACTION_CALL);

                phone_intent.setData(Uri.parse("tel:" + phone_number));

                startActivity(phone_intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg)
            {
                String phone_number = "1075";

                Intent phone_intent = new Intent(Intent.ACTION_CALL);

                phone_intent.setData(Uri.parse("tel:" + phone_number));

                startActivity(phone_intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg)
            {
                String phone_number = "1098";

                Intent phone_intent = new Intent(Intent.ACTION_CALL);

                phone_intent.setData(Uri.parse("tel:" + phone_number));

                startActivity(phone_intent);
            }
        });
    }
}