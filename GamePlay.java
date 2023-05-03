package GameBrickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private boolean play;
	private int score = 0;
	private int remainingBrick;
	private Timer timer;
	private int tempX = 0;
	private int tempY = 0;
	private int ballDirectionX;
	private int ballDirectionY;
	private Ball ball;
	private Board board;
	private BrickSet brick;
	private Random random;
	public GamePlay() {
		this.play = false;
		this.ballDirectionX = -2;
		this.ballDirectionY = -2;
		this.ball = new Ball();
		this.board = new Board();
		brick = new BrickSet(4, 5);
		this.random = new Random();
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		for (int i = 0; i < brick.getArrBrick().length; i++) {
			for (int j = 0; j < brick.getArrBrick()[0].length; j++) {
				this.remainingBrick += brick.getArrBrick()[i][j];
			}
		}
		timer = new Timer(5, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		// Tạo nền
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 700, 600);

		// Vẽ bộ gạch
		brick.drawBrick((Graphics) g);

		// Tạo khung viền
		g.setColor(Color.red);
		g.fillRect(0, 0, 6, 600);
		g.fillRect(0, 0, 700, 6);
		g.fillRect(700, 0, 6, 600);

		// Tạo bảng tính điểm
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Điểm: " + score, 600, 30);

		// Tạo thanh đỡ
		g.setColor(Color.red);
		g.fillRect(board.getBarX(), 570, 100, 6);

		// Tạo quả bóng
		g.setColor(Color.green);
		g.fillOval(ball.getBallPositionX(), ball.getBallPositionY(), 16, 16);

		if (remainingBrick == 0) {
			play = false;

			this.ballDirectionX = 0;
			this.ballDirectionY = 0;

			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("YOU WIN", 260, 330);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("NHẤN PHÍM ENTER ĐỂ TIẾP TỤC !!!", 190, 380);
		}

		if (ball.getBallPositionY() > 598) {
			play = false;
			ballDirectionX = 0;
			ballDirectionY = 0;
			this.ball.setBallPositionX(this.ball.getBallPositionX());
			this.ball.setBallPositionY(this.ball.getBallPositionY());

			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER", 230, 330);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("ĐIỂM CỦA BẠN: " + score, 205, 370);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("NHẤN PHÍM ENTER ĐỂ CHƠI LẠI !!!", 190, 420);
		}
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			if (new Rectangle(ball.getBallPositionX(), ball.getBallPositionY(), 16, 16).intersects(new Rectangle(board.getBarX(), 570, 100, 6))) {
				ballDirectionY = -ballDirectionY;
			}

			A: for (int i = 0; i < brick.getArrBrick().length; i++) {
				for (int j = 0; j < brick.getArrBrick()[0].length; j++) {
					if (brick.getArrBrick()[i][j] > 0) {
						int brickX = j * brick.getBrick().getBrickWidth() + 80;
						int brickY = i * brick.getBrick().getBrickHeight() + 50;

						int brickWidth = brick.getBrick().getBrickWidth(); // = 540/col = 27
						int brickHeight = brick.getBrick().getBrickHeight(); // = 150/row = 15

						Rectangle ballRect = new Rectangle(ball.getBallPositionX(), ball.getBallPositionY(), 16, 16);
						Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

						if (ballRect.intersects(brickRect)) {
							if (brick.getArrBrick()[i][j] == 1) {
								brick.setBrickValue(0, i, j);
								score++;
								remainingBrick--;

								if (ball.getBallPositionX() + 12 <= brickRect.x || ball.getBallPositionX() + 1 >= brickRect.x + brickRect.width) {
									ballDirectionX = -ballDirectionX;
								} else if (ball.getBallPositionX() >= brickRect.x || ball.getBallPositionX() <= brickRect.x + brickRect.width) {
									ballDirectionY = -ballDirectionY;
								}
							} else if (brick.getArrBrick()[i][j] == 2) {
								brick.setBrickValue(1, i, j);
								score++;
								remainingBrick--;

								if (ball.getBallPositionX() + 12 <= brickRect.x || ball.getBallPositionX() + 1 >= brickRect.x + brickRect.width) {
									ballDirectionX = -ballDirectionX;
								} else if (ball.getBallPositionX() >= brickRect.x || ball.getBallPositionX() <=  brickRect.x + brickRect.width) {
									ballDirectionY = -ballDirectionY;
								}
							}

							break A;
						}
					}
				}
			}
			ball.setBallPositionX(ball.getBallPositionX() + ballDirectionX);
			ball.setBallPositionY(ball.getBallPositionY() + ballDirectionY);
			if (ball.getBallPositionX() < 5) { // bóng cham lề trái
				ballDirectionX = -ballDirectionX;
			}
			if (ball.getBallPositionY() < 5) { // bóng chạm lề trên
				ballDirectionY = -ballDirectionY;
			}
			if (ball.getBallPositionX() > 686) { // bóng chạm lề phải
				ballDirectionX = -ballDirectionX;
			}
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (board.getBarX() >= 594) {
				board.setBarX(board.getBarX());
			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (board.getBarX() < 6) {
				board.setBarX(board.getBarX());
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = false;
				score = 0;
				brick = new BrickSet(brick.getRow(),brick.getCol());
				for (int i = 0; i < brick.getArrBrick().length; i++) {
					for (int j = 0; j < brick.getArrBrick()[0].length; j++) {
						this.remainingBrick += brick.getArrBrick()[i][j];
					}
				}
				board.setBarX(310);
				ball.setBallPositionX(random.nextInt(600) + 10);
				ball.setBallPositionY(random.nextInt(100) + 300);
				ballDirectionX = -2;
				ballDirectionY = -2;
				repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (remainingBrick == 0) {
				play = false;
				ballDirectionX = 0;
				ballDirectionY = 0;
				ball.setBallPositionX(ball.getBallPositionX());
				ball.setBallPositionY(ball.getBallPositionY());
			} else if (play) {
				play = false;
				tempX = ballDirectionX;
				tempY = ballDirectionY;
				ballDirectionX = 0;
				ballDirectionY = 0;
			} else {
				play = true;
				if (tempX < 0 && tempY < 0) {
					ballDirectionX = -2;
					ballDirectionY = -2;
				} else if (tempX > 0 && tempY < 0) {
					ballDirectionX = 2;
					ballDirectionY = -2;
				} else if (tempX < 0 && tempY > 0) {
					ballDirectionX = -2;
					ballDirectionY = 2;
				} else {
					ballDirectionX = 2;
					ballDirectionY = 2;
				}
			}
		}
	}

	public void moveRight() {
		if (ballDirectionX == 0 && ballDirectionY == 0) {
			play = false;
			board.setBarX(board.getBarX());
		} else {
			play = true;
			board.setBarX(board.getBarX() + 25);
		}
	}

	public void moveLeft() {
		if (ballDirectionX == 0 && ballDirectionY == 0) {
			play = false;
			board.setBarX(board.getBarX());
		} else {
			play = true;
			board.setBarX(board.getBarX() - 25);
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
