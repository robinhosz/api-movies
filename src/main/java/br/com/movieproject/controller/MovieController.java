package br.com.movieproject.controller;

import br.com.movieproject.model.MovieDTO;
import br.com.movieproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "api/v1")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{movieId}")
    public MovieDTO getMovieDetails(@PathVariable Long movieId) {
        return movieService.getMovieDetails(movieId);
    }

    @GetMapping("/movie/{id}/poster")
    public ResponseEntity<ByteArrayResource> getMoviePoster(@PathVariable Long id) {

            MovieDTO movieDetails = movieService.getMovieDetails(id);
            String posterPath = movieDetails.getPoster_path();
            if (posterPath == null) {
                return ResponseEntity.notFound().build();
            }
             byte[] response = restTemplate.getForEntity(
                "https://image.tmdb.org/t/p/w500" + posterPath, byte[].class).getBody();

        ByteArrayResource resource = new ByteArrayResource(response);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(response.length);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}