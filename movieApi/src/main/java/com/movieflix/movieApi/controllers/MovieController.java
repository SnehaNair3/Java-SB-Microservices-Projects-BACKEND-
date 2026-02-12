package com.movieflix.movieApi.controllers;


import com.movieflix.movieApi.dto.MovieDto;
import com.movieflix.movieApi.entities.Movie;
import com.movieflix.movieApi.exceptions.EmptyFileException;
import com.movieflix.movieApi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //adding movie to DB
    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file,
                                                    @RequestPart String movieDto) throws IOException, EmptyFileException {

        if(file.isEmpty()){
            throw new EmptyFileException("File is empty! Please send another file");
        }
        MovieDto dto = convertToMovieDto(movieDto);

        return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieHandler(@PathVariable Integer movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler(){
      return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<MovieDto> updateMovieHandler(@PathVariable Integer movieId,
                                                       @RequestPart MultipartFile file,
                                                       @RequestPart String movieDtoObj) throws IOException {

        if(file == null || file.isEmpty()){
            file=null;
        }

        MovieDto movieDto=convertToMovieDto(movieDtoObj);

        return ResponseEntity.ok(movieService.updateMovie(movieId,movieDto,file));
    }


    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }


    //method to convert String to JSON object
    private MovieDto convertToMovieDto(String movieDtoObj){

         //spring provides an object mapper
        ObjectMapper objectMapper=new ObjectMapper();

        return objectMapper.readValue(movieDtoObj,MovieDto.class);
    }
}
