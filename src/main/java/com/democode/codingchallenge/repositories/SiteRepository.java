package com.democode.codingchallenge.repositories;

import com.democode.codingchallenge.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, String> {
}
