package net.slipp.domain.board;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.slipp.support.jpa.CreatedAndUpdatedDateEntityListener;
import net.slipp.support.jpa.HasCreatedAndUpdatedDate;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Board implements HasCreatedAndUpdatedDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "board_type", columnDefinition = BoardType.COLUMN_DEFINITION)
	private BoardType boardType;
	
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "board_content_holder", joinColumns = @JoinColumn(name = "board_id", unique = true))
    @org.hibernate.annotations.ForeignKey(name = "fk_board_content_holder_board_id")
    @Lob
    @Column(name = "contents", nullable = false)
    private Collection<String> contentsHolder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    @Column(name = "answer_count", nullable = false)
    private int answerCount = 0;

    @Column(name = "show_count", nullable = false)
    private int showCount = 0;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @OrderBy("answerId ASC")
    private List<Answer> answers;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
    
    @Column(name = "ipaddress", length=100)
    private String ipaddress;    

    public Board() {
    }

    public Board(String title, String contents) {
        this(null, title, contents);
    }
    
    public Board(Long id, String title, String contents) {
    	this.boardId = id;
        this.title = title;
        this.contentsHolder = Lists.newArrayList(contents);
    }
    
    public Board(BoardType boardType, String name, String password, String title, String contents, String ipaddress) {
    	this.boardType = boardType;
    	this.name = name;
    	this.password = password;
        this.title = title;
        this.contentsHolder = Lists.newArrayList(contents);
        this.ipaddress = ipaddress;
    }
    
	public List<Answer> getAnswers() {
        return answers;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    private boolean isEmptyContentsHolder() {
        return contentsHolder == null || contentsHolder.isEmpty();
    }

    public String getContents() {
        if (isEmptyContentsHolder()) {
            return "";
        }

        return Iterables.getFirst(contentsHolder, "");
    }

    public Long getBoardId() {
        return boardId;
    }
    
    public BoardType getBoardType() {
		return boardType;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Collection<String> getContentsHolder() {
		return contentsHolder;
	}

	public String getTitle() {
        return title;
    }

    public int getShowCount() {
        return showCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }
    
    public String getIpaddress() {
		return ipaddress;
	}

	public boolean isSamePassword(String newPassword) {
    	if (StringUtils.isBlank(password)) {
    		return false;
    	}
    	return password.equals(newPassword);
    }

    public void delete(String newPassword) {
        if (!isSamePassword(newPassword)) {
            throw new AccessDeniedException("Password mismatch");
        }
        this.deleted = true;
    }

    public void show() {
        this.showCount += 1;
    }

    public void newAnswered() {
        this.answerCount += 1;
    }

    public void deAnswered() {
        this.answerCount -= 1;
    }

    public void update(String name, String newPassword, String title, String contents) {
    	if (!isSamePassword(newPassword)) {
            throw new AccessDeniedException("Password mismatch");
        }
    	
    	this.name = name;
        this.title = title;
        this.contentsHolder = Lists.newArrayList(contents);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
		result = prime * result + ((boardType == null) ? 0 : boardType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (boardId == null) {
			if (other.boardId != null)
				return false;
		} else if (!boardId.equals(other.boardId))
			return false;
		if (boardType != other.boardType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + boardId + ", title=" + title + ", contentsHolder="
				+ contentsHolder + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", answerCount="
				+ answerCount + ", showCount=" + showCount + ", deleted=" + deleted + "]";
	}
}
