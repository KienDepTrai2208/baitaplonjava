package GameBrickBreaker;

public class Ball {
	private int ballPositionX;
	private int ballPositionY;

	public Ball() {
		this.ballPositionX = 200;
		this.ballPositionY = 350;
	}

	public int getBallPositionX() {
		return ballPositionX;
	}

	public void setBallPositionX(int ballPositionX) {
		this.ballPositionX = ballPositionX;
	}

	public int getBallPositionY() {
		return ballPositionY;
	}

	public void setBallPositionY(int ballPositionY) {
		this.ballPositionY = ballPositionY;
	}
	
}
