import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton())
		{
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null)
				{return;}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			
			//variable to hold game status. Default "false"
			boolean lost = false;
			
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				for(int i = 0; i < 9; i++){
					for (int j = 0; j < 9; j++){
						Color newColor = null;
						switch (generator.nextInt(3)) {
						case 0:
							newColor = Color.BLUE;
							if (myPanel.colorArray[i][j].equals(newColor)){
								break;
							}
							break;
						case 1:
							newColor = Color.RED;
							if (myPanel.colorArray[i][j].equals(newColor)){
								break;
							}
							break;
						case 2:
							newColor = Color.GREEN;
							if (myPanel.colorArray[i][j].equals(newColor)){
								break;
							}
							break;
						}
						myPanel.colorArray[i][j] = newColor;
						myPanel.repaint();
					}
				}
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
							Color newColor = null;
							switch (generator.nextInt(5)) {
							case 0:
								newColor = Color.YELLOW;
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)){
									break;
								}
								break;
							case 1:
								newColor = Color.MAGENTA;
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)){
									break;
								}
								break;
							case 2:
								newColor = Color.BLACK;
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)){
									break;
								}
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)){
									break;
								}
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)){
									break;
								}
								break;
							}
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}