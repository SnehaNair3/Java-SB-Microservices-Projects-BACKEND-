package com.movieflix.movieApi.service;

import com.movieflix.movieApi.dto.MovieDto;
import com.movieflix.movieApi.entities.Movie;
import com.movieflix.movieApi.exceptions.FileExistsException;
import com.movieflix.movieApi.exceptions.MovieNotFoundException;
import com.movieflix.movieApi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{


    private final MovieRepository movieRepository;

    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {

        // 1- upload the file
        //check if the file already exists instead of standard copy - replace existing
        //ensures no duplicate files
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists! Please enter another file name.");
        }

        String uploadedFileName= fileService.uploadFile(path,file);


        // 2- set the value of field 'poster' as fileName
        movieDto.setPoster(uploadedFileName);

        // 3- map movieDto to Movie object
        Movie movie=new Movie(
               null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );
        // movieId is null here because it is a primary key and it will be auto generated
        // If we provide a movieId which already exists, then save method will update movie instead of adding.
        // since save method is used for add as well as update.
        // so give movieId null , it will be auto-generated.


        // 4 -save the movie object -> saved Movie object returned
        Movie savedMovie= movieRepository.save(movie);

        // 5- In Movie object , no posterUrl present, but in dto it is present and dto is returned.so generate the posterUrl
        String posterUrl=baseUrl + "/file/" + uploadedFileName;

        // 6 -map Movie object to dto object and return dto object.
        MovieDto  response=new MovieDto(
             savedMovie.getMovieId(),
             savedMovie.getTitle(),
             savedMovie.getDirector(),
             savedMovie.getStudio(),
             savedMovie.getMovieCast(),
             savedMovie.getReleaseYear(),
             savedMovie.getPoster(),
             posterUrl
        );

        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {

        // 1- check the data in DB and if exists, fetch the data of given ID.
        Movie movie=   movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with ID : "+ movieId));


        // 2- generate poster url
        String posterUrl=baseUrl + "/file/" + movie.getPoster();

        // 3- map to MovieDto object and return it
        MovieDto  response=new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {

        // 1- fetch the data from DB
       List<Movie> movies= movieRepository.findAll();

       List<MovieDto> movieDtos=new ArrayList<>();
        // 2- iterate through the list, generate posterUrl for each movie object and map to MovieDto object
        for(Movie movie : movies){
            String posterUrl=baseUrl + "/file/" + movie.getPoster();

            MovieDto  movieDto=new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            );

            movieDtos.add(movieDto);
        }

        return movieDtos;
    }

    @Override
    public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {
         // 1- check if the movie object exists with given movieId
        Movie mv=   movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with ID : "+ movieId));

        // 2- If file is null, do nothing
              // If file is not null, delete existing file associated with the record and upload the new file.
        String fileName=mv.getPoster();
        if(file !=null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName=fileService.uploadFile(path,file);
        }

        // 3- set movieDto's poster value,according to step 2
        movieDto.setPoster(fileName);

        // 4- map it to movie object
        Movie  movie=new Movie(
                mv.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );

        // 5- save the movie object  -> return saved movie object
        Movie updatedMovie=  movieRepository.save(movie);

        // 6- generate poster url for it
        String posterUrl=baseUrl + "/file/" + fileName;

       // 7- map to movieDto and return it
        MovieDto  response=new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public String deleteMovie(Integer movieId) throws IOException {

        // 1- check if the movie object exists in DB
        Movie mv=movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with ID : "+ movieId));
        Integer id=mv.getMovieId();

        // 2- delete the file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + mv.getPoster()));

        // 3- delete the movie object
        movieRepository.delete(mv);

        return "Movie deleted with ID : "+ id;
    }
}
