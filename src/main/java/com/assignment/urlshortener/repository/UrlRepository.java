package com.assignment.urlshortener.repository;

import com.assignment.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
