package Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DreamPackage.colorBomb;

public class colorBombTest {

	private static colorBomb c1;
	
	@Before
	public void setUp() throws Exception {
		
		c1 = new colorBomb();
	}

	@Test
	public void testToString() {
		Assert.assertEquals(
				"returns X", "X",
				c1.toString());
	}

	@Test
	public void testSpecialEffect() {
		fail("Not yet implemented");
	}

	@Test
	public void testColorBomb() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCopy() {
		fail("Not yet implemented");
	}

}
