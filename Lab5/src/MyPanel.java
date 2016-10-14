import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];

	//Array to hold game arrays numbers
	public int[][] numberArray = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	public int[][] bombArray = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	public int bombCount;

	//variable to hold game statuses
	public boolean lost = false;
	public boolean playing = false;

	public void recursiveSearch(int x ,int y) {
		if (x < 0 || x > numberArray.length || y < 0 || y > numberArray.length) {
			return; //Check for borders in order to avoid OutOfIndex or StackOverflow errors
		} else {
			int xMin = Math.max(x - 1, 0);
			int xMax = Math.min(x + 1, numberArray.length - 1);
			int yMin = Math.max(y - 1, 0);
			int yMax = Math.min(y + 1, numberArray[x].length - 1);

			if ( numberArray[x][y] != -1 && colorArray[x][y].equals(Color.WHITE) && numberArray[x][y] <= 1) {
				colorArray[x][y] = Color.GRAY;
				recursiveSearch(xMax,y);
				recursiveSearch(xMin,y);
				recursiveSearch(x,yMax);
				recursiveSearch(x,yMin);
			} else {
				return;
			}
		}
	}

	public void setNumberArray() {

		for (int x = 0; x < numberArray.length; x++){
			for (int y = 0; y < numberArray[x].length; y++){
				bombCount = 0;
				if (x == 0 && y == 8) {
					//bottom left cell
					for (int xCoord = x; xCoord <= (x + 2); xCoord++) {
						for (int yCoord = y - 2; yCoord <= (y); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (x == 8 && y == 8) {
					//bottom right cell
					for (int xCoord = x - 2; xCoord <= (x); xCoord++) {
						for (int yCoord = y - 2; yCoord <= (y); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (x == 8 && y == 0) {
					//top right cell
					for (int xCoord = (x - 2); xCoord <= (x); xCoord++) {
						for (int yCoord = y; yCoord <= (y + 2); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (x == 0 && y == 0) {
					//top left cell
					for (int xCoord = x; xCoord <= (x + 2); xCoord++) {
						for (int yCoord = y; yCoord <= (y + 2); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				}
				else if (x == 8) {
					//cells from [8, 1] to [8, 7] (right border)
					for (int xCoord = x - 2; xCoord <= (x); xCoord++) {
						for (int yCoord = y - 1; yCoord <= (y + 1); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (y == 8) {
					//cells from [1, 8] to [7, 8] (bottom border)
					for (int xCoord = x - 1; xCoord <= (x + 1); xCoord++) {
						for (int yCoord = (y - 2); yCoord <= (y); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (x == 0) {
					//cells from [0, 1] to [0, 7] (left border)
					for (int xCoord = x; xCoord <= (x + 2); xCoord++) {
						for (int yCoord = (y -1); yCoord <= (y + 1); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				} else if (y == 0) {
					//cells from [1, 0] to [7, 0] (right border)
					for (int xCoord = (x - 1); xCoord <= (x + 1); xCoord++) {
						for (int yCoord = (y); yCoord <= (y + 2); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}
				}else {
					//every other cell inside the grid
					for (int xCoord = (x - 1); xCoord <= (x + 1); xCoord++) {
						for (int yCoord = (y - 1); yCoord <= (y + 1); yCoord++) {
							if (bombArray[xCoord][yCoord] == 1) {
								bombCount++;
								numberArray[xCoord][yCoord] = -1;
							}
						}
					}

				}
				numberArray[x][y] = bombCount;
			}
		}
	}

	public MyPanel() {   //This is the constructor... this code runs first to initialize

		
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right -1 ;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width +1, height + 1);

		//Draw the grid
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}

		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if ((x == 0) || (y != TOTAL_ROWS)) {
					Color c = colorArray[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
					if (lost) { //manage when user looses
						if (bombArray[x][y] == 1){
							g.setColor(Color.BLACK);
							g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
						} else {
							g.setColor(Color.WHITE);
							g.drawString(numberArray[x][y]+"", x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 11, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 20);
						}
					} else { //manage everything else
						if (numberArray[x][y] == 0) {
							g.setColor(Color.WHITE);
							g.drawString("", x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 11, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 20);
						} else if (colorArray[x][y].equals(Color.RED)) {
							// do nothing
						} else {
							g.setColor(Color.WHITE);
							g.drawString(numberArray[x][y]+"", x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 11, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 20);
						}
					}

				}
			}
		}
	}
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS -1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1 ) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}