package com.example.movies.controllers;


import com.example.movies.models.Movie;
import com.example.movies.models.Reply;
import com.example.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Reply> newMovie(@RequestParam  String title, @RequestParam String rating, @RequestParam int duration){
        Movie movie = new Movie(title,rating,duration);
        Reply reply = movieService.addNewMovie(movie);
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies(){
//        List<Movie> movies = movieService.getAllMovies();
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMoviesByDuration(@RequestParam int maxDuration){
        List<Movie> movies = movieService.getAllMovies(maxDuration);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reply> deleteMovieById(@PathVariable int id){
        Reply reply = movieService.deleteMovieById(id);
        return new ResponseEntity<>(reply, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reply> updateMovieById(@PathVariable int id, @RequestParam  String title, @RequestParam String rating, @RequestParam int duration){
        Reply reply = movieService.updateMovieById(id, title, rating, duration);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }


}
