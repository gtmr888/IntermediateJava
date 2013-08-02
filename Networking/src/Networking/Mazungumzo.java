	package Networking;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Mazungumzo extends JFrame implements ActionListener{
		ChatClient client;
		JTextArea pane;
		JTextField input;
		
	public static void main(String[] args) {
		new Mazungumzo();

	}
	public Mazungumzo(){
		super("Mazungumzo");
		setSize(new Dimension(400, 600));
		setLayout(new BorderLayout());
		inComp();
		String ip = JOptionPane.showInputDialog("What's the Server's address?");
		client = new ChatClient(ip);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTask(), 0, 500);
		
		setVisible(true);
	}
class UpdateTask extends TimerTask {

	@Override
	public void run() {
		update();
		
	}
	
}
	public void update(){
		String text = client.recieve();
		while (text != null) {
			pane.setText(pane.getText() + "\n" + text);
			text = client.recieve();
		}
	}
	private void inComp() {
		pane = new JTextArea(34, 20);
		pane.setEditable(false);
		pane.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(pane);
		add(scroll, BorderLayout.NORTH);
		
		input = new JTextField(30);
		input.addActionListener(this);
		add(input, BorderLayout.SOUTH);
			
		
	}
	public void actionPerformed(ActionEvent e){
		if ( e.getSource() == input){
			client.send(input.getText());
			input.setText("");

		}
		
	}

}
