package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Play extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;
	
	private int totalBrick = 21;
	
	private Timer time;
	private int delay = 10;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private Block block;
	
	
	public Play(){
		
		block = new Block(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics G){
		
		//bground
		G.setColor(Color.gray);
		G.fillRect(1, 1, 692, 592);
		
		//drawing map
		block.draw((Graphics2D)G);
		
		//borders..
		G.setColor(Color.yellow);
		G.fillRect(0, 0, 3, 592);
		G.fillRect(0, 0, 692, 3);
		G.fillRect(691, 0, 3, 592);
		
		//scores
		G.setColor(Color.WHITE);
		G.setFont(new Font("serif", Font.BOLD, 25));
		G.drawString(""+score, 590, 30);
		
		//paddle
		G.setColor(Color.green);
		G.fillRect(playerX, 550, 100, 8);
		
		//the ball
		G.setColor(Color.yellow);
		G.fillOval(ballposX, ballposY, 20, 20);
		
		if(0 >= totalBrick){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			G.setColor(Color.RED);
			G.setFont(new Font("serif", Font.BOLD, 30));
			G.drawString("You WON: "+score, 260, 300);
			
			G.setFont(new Font("serif", Font.BOLD, 20));
			G.drawString("Press Enter to Restart", 230, 300);
		}
		
		if(570 < ballposY){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			G.setColor(Color.RED);
			G.setFont(new Font("serif", Font.BOLD, 30));
			G.drawString("Game Over, Scores: "+score, 190, 300);
			
			G.setFont(new Font("serif", Font.BOLD, 20));
			G.drawString("Press Enter to Restart", 230, 350);
		}
		
		
		G.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		time.start();//start
		if(play){
			if(new Rectangle(ballposX,ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
				ballYdir = -ballYdir;
			}
			
			A: for(int i=0; i < block.map.length; i++){
				for(int j = 0; j < block.map[0].length; j++){
					if(0 < block.map[i][j]){
						int brickX = j * block.brickWidth + 80;
						int brickY = i * block.brickHeight + 50;
						int brickWidth = block.brickWidth;
						int brickHeigth = block.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeigth);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if (ballRect.intersects(brickRect)) {
							block.setBrickValue(0, i, j);
							totalBrick--;
							score +=5;
							
							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}else{
								ballYdir = -ballYdir; 
							}
							
							break A;
						}
					}
				}
			}
			
			
				ballposX += ballXdir;
				ballposY += ballYdir;
				
				if(0 > ballposX){
					ballXdir = -ballXdir;
				}
				if(0 > ballposY){
					ballYdir = -ballYdir;
				}
				if(670 < ballposX){
					ballXdir = -ballXdir;
				}
			
		}
		repaint();//refresh
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(KeyEvent.VK_RIGHT == e.getKeyCode()){
			if(600 <= playerX){
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(KeyEvent.VK_LEFT == e.getKeyCode()){
			if(10 > playerX){
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play){
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBrick = 21;
				block = new Block(3, 7);
				
				repaint();
			}
		}
	}
	
	private void moveLeft() {
		play = true;
		playerX -= 20;
	}

	public void moveRight(){
		play = true;
		playerX += 20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
