package com.example.vacctrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button first,second,third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        second = (Button) findViewById(R.id.faq);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, Second.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        first = (Button) findViewById(R.id.vac);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, First.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        third = (Button) findViewById(R.id.helpline);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, Third.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }
}