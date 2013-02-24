package net.slipp.domain.room;

public enum RoomType {
	out("야외", 0, 1, 11),
	greentea("그린티", 1, 1, 12),
	lavender("라벤더", 2, 1, 12),
	rosemary("로즈마리", 3, 1, 11),
	sweetgreen("스위트그린", 4, 2, 11),
	jasmine("자스민", 5, 2, 11);
	
	private String name;
	private int type;
	private int floor;
	private int imageLength;

	private RoomType(String name, int type, int floor, int imageLength) {
		this.name = name;
		this.type = type;
		this.floor = floor;
		this.imageLength = imageLength;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getImageLength() {
		return imageLength;
	}
}
