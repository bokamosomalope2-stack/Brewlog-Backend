package com.brewlog.brewlog.repository;

import com.brewlog.brewlog.entity.Brewlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrewRepository extends JpaRepository<Brewlog, Long> {
//    Optional< Brewlog> findByNameIgnoreCase(String beans);

}
