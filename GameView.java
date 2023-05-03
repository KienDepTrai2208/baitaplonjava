package View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;

import GameBrickBreaker.GamePlay;

public class GameView extends JFrame{
	private GamePlay gamePlay;

	public GameView() {
		this.gamePlay = new GamePlay();
		this.init();
	}

	public void init() {
		this.setTitle("GAME BRICK BREAKER");
		this.setSize(720, 636);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
			
		URL url_logo = GameView.class.getResource("icon_logo.png");
		Image image_logo = Toolkit.getDefaultToolkit().createImage(url_logo);
		this.setIconImage(image_logo);
		
		this.setLayout(new BorderLayout());
		this.add(gamePlay, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}
