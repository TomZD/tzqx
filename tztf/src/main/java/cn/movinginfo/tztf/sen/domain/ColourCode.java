package cn.movinginfo.tztf.sen.domain;

public class ColourCode {
	private String item;
	
	private String color;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ColourCode [item=" + item + ", color=" + color + "]";
	}
	
}
