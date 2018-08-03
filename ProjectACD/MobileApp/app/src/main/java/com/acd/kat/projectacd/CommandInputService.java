package com.acd.kat.projectacd;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class CommandInputService extends IntentService {

    public CommandInputService(){super("CommandInputActivity");}

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.e("SERVICE STARTED", "Yeet");
            final String command = intent.getStringExtra("Command");
            sendSocketMessage(command);
        }
    }

    private void sendSocketMessage(String command) {
        try {
            Log.e("CREATING SOCKET", command);
            Socket socket = new Socket("10.38.120.108", 6364);
            socket.connect(null);
            Log.e("DOS CREATED", command);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            Log.e("DOS STARTED", command);
            DOS.writeUTF(command);
            Log.e("DOS ENDED", command);
            socket.close();
            Log.e("SOCKET CLOSED", command);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
