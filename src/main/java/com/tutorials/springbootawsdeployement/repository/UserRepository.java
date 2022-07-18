package com.tutorials.springbootawsdeployement.repository;

import com.tutorials.springbootawsdeployement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}