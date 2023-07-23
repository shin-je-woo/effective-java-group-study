package chapter12.item89;

import java.io.Serializable;

public final class MySingleton implements Serializable {

	private static final MySingleton INSTANCE = new MySingleton();
	
	private MySingleton() {
	}

	public static MySingleton getINSTANCE() {
		return INSTANCE;
	}
	
	private Object readResolve() {
		return INSTANCE;
	}
}

