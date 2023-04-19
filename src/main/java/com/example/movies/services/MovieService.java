package com.example.movies.services;


import com.example.movies.models.Movie;
import com.example.movies.models.Reply;
import com.example.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService(){

    }

    public Reply addNewMovie(Movie movie){
        movieRepository.save(movie);
        return new Reply("new movie added");
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getAllMovies(Integer maxDuration) {
        List<Movie> allMovies =  movieRepository.findAll();
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.getDuration() > maxDuration) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public Optional<Movie> getMovieById(int id){
        return movieRepository.findById(id);
    }

    public Reply deleteMovieById(int id){
        movieRepository.deleteById(id);
        return new Reply("movie deleted");
    }

    public Reply updateMovie(Movie movie){
        movieRepository.save(movie);
        return new Reply("movie " + movie.getId() + " updated");
    }


    public Reply updateMovieById(int id, String title, String rating, int duration) {
        Movie movie = movieRepository.findById(id).get();
        movie.setTitle(title);
        movie.setDuration(duration);
        movie.setRating(rating);
        movieRepository.save(movie);
        return new Reply("movie updated");
    }


}
