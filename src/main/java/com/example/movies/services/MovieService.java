package com.example.movies.services;


import com.example.movies.models.Movie;
import com.example.movies.models.Reply;
import com.example.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Movie> getAllMovies(int maxDuration) {
        List<Movie> list =  movieRepository.findAll();
        List<Movie> newList = movieRepository.findAll();
        for (Movie movie : list){
            if (movie.getDuration()> maxDuration){
                newList.remove(movie);
            }
        }return newList;
    }

    public Optional<Movie> getMovieById(int id){
        return movieRepository.findById(id);
    }

    public Reply deleteMovieById(int id){
        movieRepository.deleteById(id);
        return new Reply("movie deleted");
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
