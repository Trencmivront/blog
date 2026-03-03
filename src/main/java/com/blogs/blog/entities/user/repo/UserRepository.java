package com.blogs.blog.entities.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.blog.entities.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
// This one should also return one?
	Optional<User> findByUsername(String username);
//	it only returns one
	Optional<User> findByEmail(String email);
	
}
