package system787.flatlaf;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Dark extends FlatDarculaLaf {
	public static final String NAME = "Dark";

	public static boolean setup() {
		return setup( new Dark() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, Dark.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
