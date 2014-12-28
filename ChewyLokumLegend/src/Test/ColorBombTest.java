package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DreamPackage.ColorBomb;

public class ColorBombTest {

	private static ColorBomb c1;

	@Before
	public void setUp() throws Exception {

		c1 = new ColorBomb();
	}

	@Test
	public void testToString() {
		Assert.assertEquals("returns X", "X ", c1.toString());
	}

	@Test
	public void testSpecialEffect() {
	}

	@Test
	public void testColorBomb() {
	}

	@Test
	public void testCreateCopy() {
		Assert.assertEquals("It is same with the obstacle", true,
				testCreateCopyHelper());

	}

	private boolean testCreateCopyHelper() {
		if (ColorBomb.colorBombColor == c1.createCopy().getColor()
				&& c1.createCopy().repOK()) {
			return true;

		}

		else
			return false;

	}
}
