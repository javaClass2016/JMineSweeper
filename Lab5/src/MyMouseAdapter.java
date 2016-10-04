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
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
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
				for(int i = 1; i < 10; i++){
					for (int j = 1; j < 10; j++){
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
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} else {
							//On the grid other than on the left column and on the top row:
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
						if(gridX==0 && gridY!=0 && gridY!=10) {
							for (int i = 1; i < myPanel.colorArray.length; i++){
								Color newColor = null;
								switch (generator.nextInt(5)) {
								case 0:
									if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.YELLOW)){
										break;
									} else {
										newColor = Color.YELLOW;
										break;
									}
									
								case 1:
									if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.MAGENTA)){
										break;
									} else {
										newColor = Color.MAGENTA;
										break;
									}
									
								case 2:
									if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLACK)){
										break;
									} else {
										newColor = Color.BLACK;
										break;
									}
									
								case 3:
									if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(new Color(0x964B00))){
										break;
									} else {
										newColor = new Color(0x964B00);
										break;
									}
									
								case 4:
									if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(new Color(0xB57EDC))){
										break;
									} else {
										newColor = new Color(0xB57EDC);
										break;
									}
									
								}
								myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}
						if(gridY==0 && gridX!=0 && gridX!=10) {
							for (int i = 1; i < myPanel.colorArray.length; i++){
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
								myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor;
								myPanel.repaint();
							}
						}
						if (gridX==0 && gridY==0){
							for (int i = 1; i < myPanel.colorArray.length; i++){
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
								myPanel.colorArray[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+i] = newColor;
								myPanel.repaint();
							}
						}
						if(gridX==0 && gridY==10){
							for(int i = 4; i < 7; i++){
								for (int j = 4; j < 7; j++){
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
									myPanel.colorArray[i][j] = newColor;
									myPanel.repaint();
								}
							}
						}
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