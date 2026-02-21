package com.blogs.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.blog.entities.Blogs;

@Repository
public interface BlogsRepository  extends JpaRepository<Blogs, Long>{

}
