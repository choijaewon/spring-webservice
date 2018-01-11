package com.mocoplex.webservice.domain.posts;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long>{	
	
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	Stream<Posts> findAllDesc();
}
