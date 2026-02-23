package com.blogs.blog.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Builder
@Data
// Error: No default constructor for User
// But why? I have @Data. What else do you want?
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	// validating inputs 
	@Column
	@NotNull(message = "Name is required.")
	@NotEmpty(message = "Name is required.")
	@Pattern(regexp="^[A-Za-zÇçĞğİıÖöŞşÜü]*$", message = "Name cannot contain special letters or numbers.")
	private String name;
	
	@Column
	@Pattern(regexp="^[A-Za-zÇçĞğİıÖöŞşÜü]*$", message = "Surname cannot contain special letters or numbers.")
	private String surname;
	
	@Column
	@NotNull(message = "Username is required.")
	@NotEmpty(message = "Username is required.")
	@Size(min = 4, max = 16, message = "Username length must be between 4-16 characters.")
	@Pattern(regexp="^[A-Za-zÇçĞğİıÖöŞşÜü]*$", message = "Username cannot contain special letters or numbers.")
	private String username;
	
	@Column
	@Email(message = "Please write a valid email address.")
	@NotNull(message = "e-mail is required.")
	@NotEmpty(message = "e-mail is required.")
	private String email;
	
	@Column
	@NotNull(message = "Password is required.")
	@NotEmpty(message = "Password is required.")
	@Size(min = 8, max = 24, message = "Password length must be between 8-24 characters.")
	private String password;
	
	@Column
	@DateTimeFormat
	@CreatedDate
	private final LocalDateTime createDate = LocalDateTime.now();
	
	@OneToMany(orphanRemoval = true,
			mappedBy = "author", cascade = CascadeType.ALL)
	private List<Blogs> blogs = new ArrayList<Blogs>();

}