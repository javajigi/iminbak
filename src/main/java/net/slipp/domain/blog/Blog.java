package net.slipp.domain.blog;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.slipp.support.jpa.CreatedDateEntityListener;
import net.slipp.support.jpa.HasCreatedDate;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
@EntityListeners({ CreatedDateEntityListener.class })
public class Blog implements HasCreatedDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "blog_content_holder", joinColumns = @JoinColumn(name = "blog_id", unique = true))
    @org.hibernate.annotations.ForeignKey(name = "fk_blog_content_holder_blog_id")
    @Lob
    @Column(name = "contents", nullable = false)
    private Collection<String> contentsHolder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;
    
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
    
    public void setContents(String contents) {
    	this.contentsHolder = Lists.newArrayList(contents);
    }

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Date getCreatedDate() {
		return new Date();
	}

	public void update(Blog blog) {
		this.title = blog.title;
		this.contentsHolder = blog.contentsHolder;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", title=" + title + ", contentsHolder="
				+ contentsHolder + ", createdDate=" + createdDate + "]";
	}
}
