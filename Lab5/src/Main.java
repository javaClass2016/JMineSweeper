import javax.swing.JFrame;

import java.awt.BorderLayout;

 									 //

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("      Minesweeper      ");
		
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//myFrame.setLocation(400, 150);
		myFrame.setSize(800, 800);
		
		myFrame.setLocationRelativeTo(null); 					 //centraliza el JFrame
		MyPanel myPanel = new MyPanel();			//crea variable para la clase MyPanel
		myFrame.add(myPanel);							//añade MyPanel(es como un background) dentro del JFrame
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}