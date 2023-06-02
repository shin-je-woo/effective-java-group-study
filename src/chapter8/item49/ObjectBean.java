package chapter8.item49;

import java.util.Objects;

public class ObjectBean {
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = Objects.requireNonNull(str,"null이면 안돼!");
	}
}
