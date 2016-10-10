import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;  									 //

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		JButton restart= new JButton( "Restart");  			//
		
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);
		
		myFrame.add(restart,BorderLayout.NORTH);
		myFrame.setLocationRelativeTo(null); 					 //centraliza el JFrame
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}