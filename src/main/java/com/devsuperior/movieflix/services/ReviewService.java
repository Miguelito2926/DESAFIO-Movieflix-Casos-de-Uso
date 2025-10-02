package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AuthService authService;

    public ReviewService(ReviewRepository reviewRepository, AuthService authService) {
        this.reviewRepository = reviewRepository;
        this.authService = authService;
    }

    @Transactional
    public ReviewDTO newReview(ReviewDTO dto) {

        Review review = new Review();
        review.setText(dto.getText());
        Movie movie = new Movie();
        movie.setId(dto.getMovieId());
        review.setMovie(movie);
        User user = authService.authenticated();
        review.setUser(user);
        review = reviewRepository.save(review);

        return new ReviewDTO(review);
    }
}
