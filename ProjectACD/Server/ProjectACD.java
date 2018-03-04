import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ProjectACD{
	
	public static void main(String args[]){	
		String msg_received = "";

		Socket clientSocket;
		try {
			while(true){
				System.out.println("Started!");
				ServerSocket socket = new ServerSocket(6364);
				
				System.out.println("Socket created. Waiting for input");
				clientSocket = socket.accept();
				
				System.out.println("Socket connection established");
				DataInputStream DIS = new DataInputStream(clientSocket.getInputStream());
				Runtime rt = Runtime.getRuntime();
		        try {
		        	doSomething(rt, msg_received);
		        }
		        catch (Throwable t) {
		            System.out.println("Something failed");
		        }
		        
				msg_received = DIS.readUTF();
				clientSocket.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       //This is blocking. It will wait.
	}
	
	public static void doSomething(Runtime rt, String msg_received) throws IOException{
		switch(msg_received){
	    	case "sos":
	    		rt.exec("cmd.exe /c echo Save Our Server!" );	
				break;
	    	case "omw":
	    		rt.exec("cmd.exe /c echo On My Way!");
	    		break;
	    	case "safe":
	    		rt.exec("cmd.exe /c echo iiiiiiim SAFE!");
	    		break;
			default:
				System.err.println("What 2 heck");
		}
	}
}