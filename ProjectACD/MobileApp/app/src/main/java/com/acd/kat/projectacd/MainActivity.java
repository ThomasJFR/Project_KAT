package com.acd.kat.projectacd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button homeButton; //Make new button
    View.OnClickListener buttonListener = new View.OnClickListener() { //Button Listener
        @Override
        public void onClick(View view) { //button event handler
            switch(view.getId()){
                case R.id.homebutton: //if pressed the home button
                        Intent hb = new Intent(MainActivity.this, CommandInputActivity.class); //what the button runs
                        startActivity(hb); //executing the intent
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = (Button)findViewById(R.id.homebutton);

        homeButton.setOnClickListener(buttonListener);
    }
}
