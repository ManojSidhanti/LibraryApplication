package org.springframework.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.java.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}
