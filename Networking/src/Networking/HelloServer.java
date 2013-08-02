package Networking;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class HelloServer {
	public static void main(String[] args){
		try {
			// My IP: 10.1.10.54
			ServerSocket server = new ServerSocket(9090);
			System.out.println("Listening on port 9090 with address" + server.getInetAddress());
			while(true){
				Socket client = server.accept();
				PrintWriter output = new PrintWriter(client.getOutputStream(), true);
				output.println("Soham wants to Chat with You!");
				client.close();
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
}
