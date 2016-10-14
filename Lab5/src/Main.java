//import javax.swing.Timer;  
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Minesweeper");
		JLabel lab = new JLabel("Bombs");
		JLabel time = new JLabel("Time");
		//JLabel currentTime = new JLabel("Time running");    
		JPanel p = new JPanel(new GridBagLayout());
		JPanel p2=new JPanel(new GridBagLayout());
		
		JButton reset= new JButton("Restart");
		
		
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setSize(430, 390);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		//Setup game board
		Random rand = new Random();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				myPanel.bombArray[x][y] = rand.nextInt(4);
			}
		}
		myPanel.setNumberArray();
		
		
		GridBagConstraints c=new GridBagConstraints();
		
		//crea el JPanel de mano derecha
		p.setBackground(Color.CYAN);
		c.insets=new Insets(200,10,10,10);		
		c.gridx=0;
		c.gridy=1;
		myFrame.add(p,BorderLayout.EAST);	//  #1
		
		//aqui va la caja de el # de bombas		#2
		p.add(reset,c);
		c.gridx=0;
		c.gridy=3;
		p.add(lab);							//  #3
		
		
		//crea el JPanel de abajo
		c.insets=new Insets(10,-100,10,10);	
		
		myFrame.add(p2,BorderLayout.SOUTH);		//#4
		c.gridx=1;
		c.gridy=0;
		p2.add(time,c);					
		
		//c.gridx=2;
		//c.gridy=0;
		//p2.add(currentTime);					//#5
			
		
		myFrame.setVisible(true);
	}
}