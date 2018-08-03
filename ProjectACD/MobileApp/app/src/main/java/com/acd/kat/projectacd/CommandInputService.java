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
//            final String action = intent.getAction();
            sendSocketMessage();
        }
    }

    private void sendSocketMessage() {
        try {
            Log.e("SOCKET MESSAGE STARTED", "Yeet");
            Socket socket = new Socket("192.168.1.78", 6364); //sort this command into another class, as main file is lagging. *along w/other commands
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF("SOS");
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
