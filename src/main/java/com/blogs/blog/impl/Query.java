package com.blogs.blog.impl;

public interface Query<I, O>{
	
	public O execute(I i);

}
