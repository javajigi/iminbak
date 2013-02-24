package net.slipp.domain.qna;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.slipp.domain.user.SocialUser;
import net.slipp.support.jpa.CreatedAndUpdatedDateEntityListener;
import net.slipp.support.jpa.HasCreatedAndUpdatedDate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDeniedException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Question implements HasCreatedAndUpdatedDate {
	private static final Integer DEFAULT_BEST_ANSWER = 2;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "fk_question_writer")
    private SocialUser writer;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "question_content_holder", joinColumns = @JoinColumn(name = "question_id", unique = true))
    @org.hibernate.annotations.ForeignKey(name = "fk_question_content_holder_question_id")
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

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @OrderBy("answerId ASC")
    private List<Answer> answers;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Question() {
    }

    public Question(SocialUser loginUser, String title, String contents) {
        this(null, loginUser, title, contents);
    }
    
    public Question(Long id, SocialUser loginUser, String title, String contents) {
    	this.questionId = id;
        this.writer = loginUser;
        this.title = title;
        this.contentsHolder = Lists.newArrayList(contents);
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

    public Long getQuestionId() {
        return questionId;
    }

    public boolean isWritedBy(SocialUser socialUser) {
        return writer.isSameUser(socialUser);
    }

    public SocialUser getWriter() {
        return writer;
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

    public void delete(SocialUser loginUser) {
        if (!isWritedBy(loginUser)) {
            throw new AccessDeniedException(loginUser.getDisplayName() + " is not owner!");
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

    public void update(SocialUser loginUser, String title, String contents) {
        if (!isWritedBy(loginUser)) {
            throw new AccessDeniedException(loginUser.getDisplayName() + " is not owner!");
        }
    	
        this.title = title;
        this.contentsHolder = Lists.newArrayList(contents);
    }

    public Set<SocialUser> findNotificationUser(SocialUser loginUser) {
        Answers newAnswers = new Answers(this.answers);
        Set<SocialUser> notifierUsers = newAnswers.findFacebookAnswerers();
        notifierUsers.add(this.writer);
        return Sets.difference(notifierUsers, Sets.newHashSet(loginUser));
    }

    /**
     * 베스트 댓글 하나를 반환한다.
     * 
     * @return
     */
    public Answer getBestAnswer() {
        if (CollectionUtils.isEmpty(getAnswers())) {
            return null;
        }

        Answer answer = getTopLikeAnswer();
        if (!answer.likedMoreThan(DEFAULT_BEST_ANSWER)) {
            return null;
        }

        return answer;
    }

    private Answer getTopLikeAnswer() {
        List<Answer> sortAnswers = Lists.newArrayList();
        sortAnswers.addAll(getAnswers());
        Collections.sort(sortAnswers);
        return sortAnswers.get(0);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerCount;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((contentsHolder == null) ? 0 : contentsHolder.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + showCount;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
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
		Question other = (Question) obj;
		if (answerCount != other.answerCount)
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
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
		if (deleted != other.deleted)
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (showCount != other.showCount)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writer=" + writer + ", title=" + title + ", contentsHolder="
				+ contentsHolder + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", answerCount="
				+ answerCount + ", showCount=" + showCount + ", answers=" + answers + ", deleted=" + deleted + "]";
	}
}
