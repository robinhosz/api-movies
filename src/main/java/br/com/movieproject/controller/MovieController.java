package br.com.movieproject.controller;

import br.com.movieproject.dto.MovieDTO;
import br.com.movieproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api/v2/movies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovieController {

    private final MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public MovieDTO getMovieDetails(@PathVariable Long movieId) {
        return movieService.getMovieDetails(movieId);
    }

    @GetMapping("/{id}/{w}/poster")
    public ResponseEntity<ByteArrayResource> getMoviePoster(@PathVariable Long id, @PathVariable String w) {
            MovieDTO movieDetails = movieService.getMovieDetails(id);
            String posterPath = movieDetails.getPoster_path();
            if (posterPath == null) {
                return ResponseEntity.notFound().build();
            }
             byte[] response = restTemplate.getForEntity(
                "https://image.tmdb.org/t/p/" + w + posterPath, byte[].class).getBody();

        ByteArrayResource resource = new ByteArrayResource(response);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(response.length);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}