package net.slipp.domain.board;

public enum BoardType {
	free,
	review;
	
	public static final String COLUMN_DEFINITION = "enum('free','review')";
}
