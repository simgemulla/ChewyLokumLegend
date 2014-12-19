package DreamPackage;

import java.awt.Color;

public abstract class SpecialLokum extends Lokum {

	private int createBonus;
	private int deleteBonus;

	public SpecialLokum(Color c) {
		super(c);
	}

	public abstract void specialEffect(int x, int y);

	/**
	 * @param bonus
	 *            Bonus points that will be awarded when this specialLokum is
	 *            created.
	 */
	public void setCreateBonus(int bonus) {
		this.createBonus = bonus;
	}

	/**
	 * @param bonus
	 *            Bonus points that will be awarded when this specialLokum is
	 *            deleted.
	 */
	public void setDeleteBonus(int bonus) {
		this.deleteBonus = bonus;
	}

	/**
	 * @return Bonus points that will be awarded when this specialLokum is
	 *         created.
	 */
	public int getCreateBonus() {
		return createBonus;
	}

	/**
	 * @return Bonus points that will be awarded when this specialLokum is
	 *         deleted.
	 */
	public int getDeleteBonus() {
		return deleteBonus;
	}

}
