import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ProjectACD{
	
	public static void main(String args[]){	
		String msg_received = "";

		Socket clientSocket;
		try {
			System.out.println("Started!");
			ServerSocket socket = new ServerSocket(6364);
			System.out.println("Socket created. Waiting for input");
			clientSocket = socket.accept();
			DataInputStream DIS = new DataInputStream(clientSocket.getInputStream());
			
			System.out.println("Input received!");
			
			Runtime rt = Runtime.getRuntime();
	        try {
	        	switch(msg_received){
		        	case "tree":
		        		rt.exec( "cmd.exe /c tree" );	
	        			break;
	    			default:
	    				System.err.println("What 2 heck");
	        	}
	        }
	        catch (Throwable t) {
	            System.out.println("Something failed");
	        }
	        
			msg_received = DIS.readUTF();
			clientSocket.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       //This is blocking. It will wait.
	}
	
	public static void doSomething(){
		
	}
}