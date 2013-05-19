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

import net.slipp.support.jpa.CreatedAndUpdatedDateEntityListener;
import net.slipp.support.jpa.HasCreatedAndUpdatedDate;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Answer implements HasCreatedAndUpdatedDate, Comparable<Answer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerId;
	
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
	
	@Column(name = "sum_like", nullable = true, columnDefinition="integer DEFAULT 0")
	private Integer sumLike = 0;
	
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

	public void answerTo(Board question) {
		this.board = question;
		question.newAnswered();
	}
	
	public void updateAnswer(Answer answerDto) {
		this.contentsHolder = answerDto.contentsHolder;
	}
	
	public Integer getSumLike() {
		return sumLike;
	}

	public void upRank() {
		this.sumLike += 1;
	}
	
	boolean likedMoreThan(int totalLiked) {
		if (getSumLike() >= totalLiked) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Answer o) {
		return o.getSumLike().compareTo(getSumLike());
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", contentsHolder=" + contentsHolder
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", question=" + board + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerId == null) ? 0 : answerId.hashCode());
		result = prime * result + ((contentsHolder == null) ? 0 : contentsHolder.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		if (contentsHolder == null) {
			if (other.contentsHolder != null)
				return false;
		} else if (!contentsHolder.equals(other.contentsHolder))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}
}
