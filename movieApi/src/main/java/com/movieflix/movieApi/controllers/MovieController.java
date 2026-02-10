package com.movieflix.movieApi.controllers;


import com.movieflix.movieApi.dto.MovieDto;
import com.movieflix.movieApi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

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
                                                    @RequestPart String movieDto) throws IOException {
        MovieDto dto = convertToMovieDto(movieDto);

        return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }


    //method to convert String to JSON object
    private MovieDto convertToMovieDto(String movieDtoObj){


         //spring provides an object mapper
        ObjectMapper objectMapper=new ObjectMapper();


        return objectMapper.readValue(movieDtoObj,MovieDto.class);
    }
}
