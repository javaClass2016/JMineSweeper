//import javax.swing.Timer;  
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {
	public static void main(String[] args) {
		SetupFrame();
	}
	
	public static void SetupFrame() {
		JFrame myFrame = new JFrame("JMineSweeper");
		JLabel lab = new JLabel("Bombs");
		JLabel time = new JLabel("Time"); 
		JPanel p = new JPanel(new GridBagLayout());
		JPanel p2=new JPanel(new GridBagLayout());
		Timer timer = new Timer(1000, null);
		JButton reset= new JButton("Restart");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setSize(430, 390);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		time.setText("Time: 0s");
		
		
		ActionListener action = new ActionListener() {
			int time_running = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!myPanel.lost) {
					time_running++;
					time.setText("Time: "+time_running+"s");
				}
			}
		};
		
		timer = new Timer(1000, action);
		timer.start();
		
		//Setup game board
		Random rand = new Random();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				myPanel.bombArray[x][y] = rand.nextInt(4);
			}
		}
		
		myPanel.setNumberArray();

		//Get mine count
		int mineQ = 0;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (myPanel.bombArray[x][y] == 1) {
					mineQ++;
				}
			}
		}
		lab.setText("Mines: " + mineQ);

		GridBagConstraints c=new GridBagConstraints();

		//crea el JPanel de mano derecha
		p.setBackground(Color.CYAN);
		c.insets=new Insets(200,10,10,10);		
		c.gridx=0;
		c.gridy=1;
		myFrame.add(p,BorderLayout.EAST); //#1

		//aqui va la caja de el # de bombas	
		p.add(reset,c); // #2
		c.gridx=0;
		c.gridy=3;
		p.add(lab);	//#3


		//crea el JPanel de abajo
		c.insets=new Insets(10,-100,10,10);	

		myFrame.add(p2,BorderLayout.SOUTH);//#4
		c.gridx=1;
		c.gridy=0;
		p2.add(time,c);
		
		myFrame.setVisible(true);
		
		//action for pushing the reset button
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				myFrame.dispose(); //"closes" the frame
				SetupFrame(); //sets up new game frame
			}
		});
	}
}