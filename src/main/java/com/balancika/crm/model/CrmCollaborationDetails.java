package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "crm_collaboration_details")
public class CrmCollaborationDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CommentID")
	private int commentId;
	
	@Column(name="PostID")
	private int postId;
	
	@Column(name="Comment")
	private String comment;
	
	@Column(name="Username")
	private String username;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CreateDate")
	private LocalDateTime createDate;
	
	@Transient
	private String formatCreateDate;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public String getFormatCreateDate() {
		return formatCreateDate;
	}

	public void setFormatCreateDate(String formatCreateDate) {
		this.formatCreateDate = formatCreateDate;
	}

}
