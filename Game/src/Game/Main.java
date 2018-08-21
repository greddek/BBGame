package Game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame object = new JFrame();
		Play play = new Play();
		object.setBounds(10, 10, 700, 600);
		object.setTitle("Game1");
		object.setResizable(false);
		object.setVisible(true);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.add(play);
		
	}

}
