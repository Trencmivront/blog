package com.blogs.blog.config;

import java.util.Arrays;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// Indicates that beans from this class may be processed in Spring container
@Configuration
// Enables caching?
@EnableCaching
// This enables detection of @Scheduled annotations on any Spring-managed bean in the container. 
@EnableScheduling
public class CacheConfiguration {
	
//	Our custom CacheManager bean
	@Bean
	public ConcurrentMapCacheManager cacheManager() {
		
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		
		cacheManager.setAllowNullValues(false);
		cacheManager.setCacheNames(Arrays.asList("userCached"));
		
		return cacheManager;
		
	}
	
//	schedules the following method
//	@Scheduled(initialDelay = 0,fixedDelay = 7000)
//	meaning that every data on cache will be evicted
//	@CacheEvict(value = "userCached", allEntries = true)
//	public void scheduledEvict() {
//		
//		System.out.println("User cache evicted.");
//		
//	}

}
