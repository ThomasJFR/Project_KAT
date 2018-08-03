package com.acd.kat.projectacd;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandInputActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_INTERNET, ok=0x0104000a, cancel=0x01040000;
    String alertTitle, alertMessage;
    ToggleButton sos, omw, safe;
    AlertDialog.Builder builder;
    View.OnClickListener toggleButton = new View.OnClickListener() {

        @Override
        public void onClick(View view) { //View = object type, view = object reference id
            switch(view.getId()) { //using object ref Id for switch case
                case R.id.S_O_S_:omw.setChecked(false);
                                 safe.setChecked(false);
                                 break;
                case R.id.OMW:  sos.setChecked(false);
                                safe.setChecked(false);
                                break;
                case R.id.SAFE:sos.setChecked(false);
                               omw.setChecked(false);
                               break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_input);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        builder = new AlertDialog.Builder(this);
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

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//permission for internet
                System.out.println("On Click successful");
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CommandInputActivity.this, Manifest.permission.INTERNET)){
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
                        ActivityCompat.requestPermissions(CommandInputActivity.this,
                                new String[]{Manifest.permission.INTERNET},
                                MY_PERMISSIONS_REQUEST_INTERNET);
                    }
                }//permission for wifi state
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CommandInputActivity.this, Manifest.permission.ACCESS_WIFI_STATE)){
                        System.out.println("Wifi State permission requesting...");
                        //Show an explanation to user as to why we need permission

                    }
                    else {
                        //if an explanation was already given, then just ask
                    }
                }
                try {
                    Socket socket = new Socket("192.168.1.78", 6364); //sort this command into another class, as main file is lagging. *along w/other commands
                    DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
                    DOS.writeUTF("SOS");
                    socket.close();
                }
                catch(IOException e){
                    //catch nothing
                }
            }
        });
        sos = (ToggleButton)findViewById(R.id.S_O_S_);
        omw = (ToggleButton)findViewById(R.id.OMW);
        safe = (ToggleButton)findViewById(R.id.SAFE);

        sos.setOnClickListener(toggleButton);
        omw.setOnClickListener(toggleButton);
        safe.setOnClickListener(toggleButton);
    }

}
