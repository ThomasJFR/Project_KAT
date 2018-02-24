package com.acd.kat.projectacd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandInputActivity extends AppCompatActivity {
    ToggleButton sos, omw, safe;
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

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Socket socket = new Socket("192.168.0.1", 6364);
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
