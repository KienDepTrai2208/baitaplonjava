package Test;

import javax.swing.UIManager;
import View.GameView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new GameView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
