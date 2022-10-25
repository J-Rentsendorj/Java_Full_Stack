package com.jinn.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {
	
	// MEMBER VARIABLES
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="Title is required")
    @Size(min=2, message="Title must be longer than 2 characters")
    private String title;
    
    @NotEmpty(message="Author is required")
    private String author;
    
    @NotEmpty(message="Please submit your thoughts")
    private String thoughts;
    
    // RELATIONSHIPS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;
    
	// CONSTRUCTORS
    // EMPTY CONSTRUCTOR
	public Book() {
		super();
	}
	// FULL CONSTREUCTOR
	public Book(
			@NotEmpty(message = "Title is required") @Size(min = 2, message = "Title must be longer than 2 characters") String title,
			@NotEmpty(message = "Author is required") String author,
			@NotEmpty(message = "Please submit your thoughts") String thoughts, User creator) {
		super();
		this.title = title;
		this.author = author;
		this.thoughts = thoughts;
		this.creator = creator;
	}
	// GETTERS SETTERS OTHER METHODS
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	  
	@PrePersist
    protected void onCreate(){
	this.createdAt = new Date();
	}
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getThoughts() {
		return thoughts;
	}
	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
