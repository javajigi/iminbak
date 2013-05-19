package net.slipp.domain.board;

import org.springframework.security.authentication.encoding.PasswordEncoder;

public class BoardDto {
	private Long boardId;
	
	private BoardType boardType;

	private String name;

	private String password;

	private String title;

	private String contents;

	public BoardDto() {
	}

	public BoardDto(Board board) {
		this.boardId = board.getBoardId();
		this.boardType = board.getBoardType();
		this.name = board.getName();
		this.title = board.getTitle();
		this.contents = board.getContents();
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	
	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Board createBoard(PasswordEncoder passwordEncoder) {
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		return new Board(boardType, name, encodedPassword, title, contents);
	}
}
