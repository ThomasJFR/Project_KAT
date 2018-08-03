package com.acd.kat.projectacd;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_INTERNET, ok=0x0104000a, cancel=0x01040000;
    String alertTitle, alertMessage;

    Button homeButton; //Make new button
    View.OnClickListener buttonListener = new View.OnClickListener() { //Button Listener
        @Override
        public void onClick(View view) { //button event handler
            switch(view.getId()){
                case R.id.homebutton: //if pressed the home button
                    if(MainActivity.this.requestPermissions()){
                        Intent hb = new Intent(MainActivity.this, CommandInputActivity.class); //what the button runs
                        startActivity(hb); //executing the intent
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = findViewById(R.id.homebutton);

        homeButton.setOnClickListener(buttonListener);
    }


    private boolean requestPermissions() {
        System.out.println("On Click successful");
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setPositiveButton(ok, new DialogInterface.OnClickListener() { //setting the yes button for the alert popup
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() { //setting the no button for the alert popup
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET)){
                //Show an explanation to user as to why we need permission
                System.out.println("Internet permission requesting...");
                alertMessage="Internet access is needed for this app to send commands globally. We will not use this for anything else. Is that okay?";
                alertTitle="Internet Access";
                builder.setMessage(alertMessage) //Message *ORIGINALLY CONTAINED 'R.string.' IN FRONT OF VARIABLE, UNKNOWN REASON*
                        .setTitle(alertTitle);  //Title
                AlertDialog dialog = builder.create(); //Alert creation
            }
            else {
                //if an explanation was already given, then just ask
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);
            }
        }//permission for wifi state
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_WIFI_STATE)){
                System.out.println("Wifi State permission requesting...");
                //Show an explanation to user as to why we need permission

            }
            else {
                //if an explanation was already given, then just ask
            }
        }

        return true;
    }
}
