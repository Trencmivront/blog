package com.blogs.blog.entities.blogs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.blogs.blog.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
public class Blog {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotEmpty(message = "Header is a must.")
	@Size(min = 3, message = "Header length must be at least 3 character!")
	private String header;
	
	@Column
	@NotEmpty(message = "Please write your content.")
	@NotNull(message = "Please write your content.")
	@Size(min = 20, message = "Body must be at least 20 character!")
	private String body;
	
//	Many blogs can map to one author
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "author")
	@NotNull
	private User author;
	
	@CreatedDate
	@DateTimeFormat
	@Column
	private final LocalDateTime createDate = LocalDateTime.now();

}