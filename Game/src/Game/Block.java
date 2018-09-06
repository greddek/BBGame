package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public Block(int row,int col){
		map = new int[row][col];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				map[i][j] = 1;
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
		
	}
	
	public void draw(Graphics2D G2){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(0 < map[i][j]){
					G2.setColor(Color.blue);
					G2.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					G2.setStroke(new BasicStroke(3));
					G2.setColor(Color.gray);
					G2.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col){
		map[row][col] = value;
	}
}
