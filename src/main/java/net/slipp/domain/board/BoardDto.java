package net.slipp.domain.board;

import net.slipp.support.utils.SecurityUtils;

import org.springframework.security.authentication.encoding.PasswordEncoder;

public class BoardDto {
	private static final int DEFAULT_WRITE_INTERVAL_SECOND = 20;

    private Long boardId;
	
	private BoardType boardType;

	private String name;

	private String password;

	private String title;

	private String contents;
	
	private String token;

	public BoardDto() {
	    this.token = SecurityUtils.createToken();
	}

	public BoardDto(Board board) {
		this.boardId = board.getBoardId();
		this.boardType = board.getBoardType();
		this.name = board.getName();
		this.title = board.getTitle();
		this.contents = board.getContents();
		this.token = SecurityUtils.createToken();
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
	
	public void setToken(String token) {
        this.token = token;
    }
	
	public String getToken() {
        return token;
    }
	
	public boolean validToken(String sessionToken) {
	    if (! SecurityUtils.validUuid(sessionToken, this.token)) {
	        return false; 
	    }
	    
	    long intervalSecond = SecurityUtils.intervalSecond(this.token);
	    if (intervalSecond < DEFAULT_WRITE_INTERVAL_SECOND) {
	        return false;
	    }
	    
	    return true;
	}
	
	public Board createBoard(PasswordEncoder passwordEncoder, String ipaddress) {
		String encodedPassword = passwordEncoder.encodePassword(password, null);
		return new Board(boardType, name, encodedPassword, title, contents, ipaddress);
	}
}
