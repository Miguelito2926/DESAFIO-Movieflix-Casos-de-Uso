package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @EntityGraph(attributePaths = "genre")
    @Query("SELECT m FROM Movie m WHERE (:genreId IS NULL OR :genreId = 0 OR m.genre.id = :genreId)")
    Page<Movie> findByGenreIdOrAll(Long genreId, Pageable pageable);
}
