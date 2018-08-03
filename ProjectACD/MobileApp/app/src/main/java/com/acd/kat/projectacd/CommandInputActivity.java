package com.acd.kat.projectacd;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//permission for internet
                Intent intent = new Intent(CommandInputActivity.this.getApplicationContext(), CommandInputService.class);
                intent.putExtra("Command", "SOS");
                CommandInputActivity.this.startService(intent);

                sos.setChecked(false);
                omw.setChecked(false);
                safe.setChecked(false);
            }
        });
        sos = findViewById(R.id.S_O_S_);
        omw = findViewById(R.id.OMW);
        safe = findViewById(R.id.SAFE);

        sos.setOnClickListener(toggleButton);
        omw.setOnClickListener(toggleButton);
        safe.setOnClickListener(toggleButton);
    }

}
