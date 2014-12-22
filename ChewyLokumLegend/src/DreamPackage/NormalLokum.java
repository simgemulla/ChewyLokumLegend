package DreamPackage;

import java.awt.Color;

public class NormalLokum extends Lokum {

	public NormalLokum(Color c) {
		super(c);
	}

	@Override
	public Lokum createCopy() {
		return new NormalLokum(this.getColor());
	}

	@Override
	public int destroy(int x, int y) {
		return 0;
	}

}
