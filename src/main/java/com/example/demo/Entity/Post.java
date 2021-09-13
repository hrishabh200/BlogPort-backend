package com.example.demo.Entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates getters and setters
@Entity //JPA annotation
@Builder //produces complex builder APIs for your classes
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //The @GeneratedValue annotation is to configure the way of increment of the specified column(field)
	private Long postId;
	@NotBlank(message= "Post name cannont be empty or NULL")
	private String postName;
	@Nullable
	    @Lob //for huge chunks of text
	    private String description;
	    private Integer voteCount = 0;
	    @ManyToOne(fetch = FetchType.LAZY) // as user can have more than one post (user-post relationship)
	    @JoinColumn(name = "userId", referencedColumnName = "userId") //joining using userId
	    private User user;
	    private Instant createdDate; //time when post is created
   @ManyToOne(fetch = FetchType.LAZY) // LAZY tells Hibernate to only fetch the related entities from the database when you use the relationship 
	    @JoinColumn(name = "subId", referencedColumnName = "subId")
	    private Community subreddit;
}
