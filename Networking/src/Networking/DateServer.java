package Networking;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;



public class DateServer {
	public static void main(String[] args){
		try {
			// My IP: 10.1.10.54
			ServerSocket server = new ServerSocket(9090);
			System.out.println("Listening on port 9090 with address" + server.getInetAddress());
			while(true){
				Socket client = server.accept();
				DateRunnable runnable = new DateRunnable(client);
				Thread t = new Thread(runnable);
				t.start();
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
}
class DateRunnable implements Runnable{
	
	PrintWriter output;
		
	public DateRunnable(Socket client){
		
		try {
			output = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		while (true) {
			Date d = new Date();
			output.println("The Current Time is " + d);
		}
	}
	
	
}

