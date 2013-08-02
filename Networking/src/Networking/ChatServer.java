package Networking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ChatServer {
	public static void main(String[] args){
		ArrayList <String> messages = new ArrayList <String> ();
		try {
			// My IP: 10.1.10.54
			ServerSocket server = new ServerSocket(9090);
			System.out.println("Listening on port 9090 with address" + server.getInetAddress());
			
			while(true){
				Socket client = server.accept();
				ChatRunnable runnable = new ChatRunnable(client, messages);
				Thread t = new Thread(runnable);
				t.start();
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
}
class ChatRunnable implements Runnable{
	BufferedReader input;
	PrintWriter output;
	ArrayList <String> chatroom;
	int position = 0;
	long lastMessageTime = 0;
	
	String lastMessageSent = "";
		
	public ChatRunnable(Socket client, ArrayList <String> room){
		
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(), true);
			chatroom = room;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		while (true) {
			String in;
			try {
				in = input.readLine();
				if (in != null){
					if (in.length() == 0){
						
					}
					else if (){
						
					}
					else{
						chatroom.add(in);
						lastMessageTime = System.currentTimeMillis();
					}
				}
				for (int x = position; x < chatroom.size(); x++){
					output.println(chatroom.get(x));
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}

