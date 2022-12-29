package com.asifekbal.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asifekbal.portfolio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
