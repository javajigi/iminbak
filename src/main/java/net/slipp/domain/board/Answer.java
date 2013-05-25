package net.slipp.domain.board;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import net.slipp.support.jpa.CreatedAndUpdatedDateEntityListener;
import net.slipp.support.jpa.HasCreatedAndUpdatedDate;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Answer implements HasCreatedAndUpdatedDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerId;
	
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    
    @Transient
    private String rawPassword;
    
    @Column(name = "password", length = 100, nullable = false)
    private String password;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "answer_content_holder", joinColumns = @JoinColumn(name = "answer_id", unique = true))
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_content_holder_answer_id")
	@Lob
	@Column(name = "contents", nullable = false)
	private Collection<String> contentsHolder;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "fk_answer_parent_id")
	private Board board;
	
	public Answer() {
	}

	public Answer(Long answerId) {
		this.answerId = answerId;
	}

	public Board getBoard() {
		return this.board;
	}

	public Long getAnswerId() {
		return answerId;
	}
	
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void encodePassword(PasswordEncoder encoder) {
		this.password = encoder.encodePassword(getRawPassword(), null);
	}
	
	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public void setContents(String newContents) {
		if (isEmptyContentsHolder()) {
			contentsHolder = Lists.newArrayList(newContents);
		} else {
			contentsHolder.clear();
			contentsHolder.add(newContents);
		}
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

	public void answerTo(Board board) {
		this.board = board;
		board.newAnswered();
	}
	
    public boolean isSamePassword(String newPassword) {
    	if (StringUtils.isBlank(password)) {
    		return false;
    	}
    	return password.equals(newPassword);
    }
	
	public void updateAnswer(Answer answerDto, PasswordEncoder encoder) {
		answerDto.encodePassword(encoder);
    	if (!isSamePassword(answerDto.password)) {
            throw new AccessDeniedException("Password mismatch");
        }
		
    	this.name = answerDto.name;
		this.contentsHolder = answerDto.contentsHolder;
	}
	
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", contentsHolder=" + contentsHolder
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerId == null) ? 0 : answerId.hashCode());
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
		Answer other = (Answer) obj;
		if (answerId == null) {
			if (other.answerId != null)
				return false;
		} else if (!answerId.equals(other.answerId))
			return false;
		return true;
	}
}
