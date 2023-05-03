package GameBrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BrickSet {
	private int arrBrick[][];
	private Brick brick;
	private int col;
	private int row;

	public BrickSet(int row, int col) {
		arrBrick = new int[row][col];
		brick = new Brick();
		this.row = row;
		this.col = col;
		for (int i = 0; i < arrBrick.length; i++) {
			for (int j = 0; j < arrBrick[0].length; j++) {
				if (i % 2 == 0) {
					arrBrick[i][j] = 1;
				}
				else {
					arrBrick[i][j] = 2;
				}
			}
		}
		brick.setBrickWidth(540 / this.col);
		brick.setBrickHeight(150 / this.row);
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Brick getBrick() {
		return brick;
	}

	public void setBrick(Brick brick) {
		this.brick = brick;
	}

	public int[][] getArrBrick() {
		return arrBrick;
	}

	public void setArrBrick(int[][] arrBrick) {
		this.arrBrick = arrBrick;
	}

	public void drawBrick(Graphics g) {
		for (int i = 0; i < arrBrick.length; i++) {
			for (int j = 0; j < arrBrick[0].length; j++) {
				if (arrBrick[i][j] > 0) {
					if(arrBrick[i][j] == 1) {
						g.setColor(Color.cyan);
						g.fillRect(j * brick.getBrickWidth() + 80, i * brick.getBrickHeight() + 50, brick.getBrickWidth(),brick.getBrickHeight());
					}
					else if(arrBrick[i][j] == 2) {
						g.setColor(Color.red);
						g.fillRect(j * brick.getBrickWidth() + 80, i * brick.getBrickHeight() + 50, brick.getBrickWidth(),brick.getBrickHeight());
					}
					
					((Graphics2D) g).setStroke(new BasicStroke(2));
					g.setColor(Color.darkGray);
					g.drawRect(j * brick.getBrickWidth() + 80, i * brick.getBrickHeight() + 50, brick.getBrickWidth(),brick.getBrickHeight());
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		arrBrick[row][col] = value;
	}

}
