package com.asifekbal.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asifekbal.portfolio.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
