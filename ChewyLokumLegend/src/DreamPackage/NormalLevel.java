package DreamPackage;

public class NormalLevel extends Level {

	public NormalLevel(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked, int specialSwapCount) {
		super(moveCount, highScore, initialBoard, scoreNeeded, levelID, locked,
				specialSwapCount);
	}

	@Override
	public void startLevel() {
		// TODO Auto-generated method stub
	}

	@Override
	public Level deepClone() {
		return new NormalLevel(this.getMoveCount(), this.getHighScore(),
				this.copyBoard(), this.getScoreNeeded(), this.getLevelID(),
				this.getLocked(), this.getSpecialSwapCount());
	}

}
