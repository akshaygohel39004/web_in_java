package com.akshay.testing.repository;

import com.akshay.testing.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeopleRepository extends JpaRepository<People, UUID> {
}
