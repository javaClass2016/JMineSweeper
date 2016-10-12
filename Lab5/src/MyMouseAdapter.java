import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	int bombCount = 0;
	
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
			//boolean lost = false;
			
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
				//Outside frame, do nothing..
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
							bombCount = 0;
							
							//Determine bomb amount around pressed cell
							for (int xCoord = Math.abs(myPanel.mouseDownGridX - 1); xCoord < (myPanel.mouseDownGridX + 2); xCoord++) {
								for (int yCoord = Math.abs(myPanel.mouseDownGridY - 1); yCoord < (myPanel.mouseDownGridY + 2); yCoord++) {
									if (myPanel.bombArray[xCoord][yCoord] == 1) {
										myPanel.colorArray[xCoord][yCoord] = Color.RED;
										bombCount++;
									} else {
										myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
									}
								}
							}
							myPanel.numberArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = bombCount;
							System.out.println("tile bomb number: " + myPanel.numberArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]);
							System.out.println(bombCount);
							System.out.println("Bomb? " + myPanel.bombArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]);
							
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