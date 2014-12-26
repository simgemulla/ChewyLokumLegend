package Test;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DreamPackage.*;

public class NormalLokumTest {

	public static Lokum Lokum1;
	public static final Color RED = new Color(204, 0, 0);
	public static final Color GREEN = new Color(76, 153, 0);
	public static final Color BROWN = new Color(102, 51, 0);
	public static final Color YELLOW = new Color(255, 153, 51);

	@Before
	public void setUp() throws Exception {

		Lokum1 = new NormalLokum(RED);

	}

	@Test
	public final void testCreateCopy() {

		Assert.assertEquals("New Lokum with same color", true,
				testCreateCopyHelper());

	}

	private boolean testCreateCopyHelper() {
		if (Lokum1.getColor() == Lokum1.createCopy().getColor()
				&& Lokum1.createCopy().repOK()) {
			return true;

		}

		else
			return false;

	}

	@Test
	public final void testGetColor() {
		Assert.assertEquals("Color of Lokum", RED, Lokum1.getColor());
	}

	@Test
	public final void testRepOK() {
		Assert.assertEquals("Lokum rep OK", true, Lokum1.repOK());
	}

	@Test
	public final void testDestroy() {

		Assert.assertEquals("Return 0", 0, Lokum1.destroy(1, 2));
	}

}
