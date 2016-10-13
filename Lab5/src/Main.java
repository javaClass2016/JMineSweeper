import java.util.Random;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Minesweeper");

		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setSize(325, 350);
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

		myFrame.setVisible(true);
	}
}