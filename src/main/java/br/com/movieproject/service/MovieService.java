package br.com.movieproject.service;

import br.com.movieproject.dto.MovieDTO;
import br.com.movieproject.proxy.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private TmdbService tmdbService;
    private final String apiKey;

    public MovieService(Environment environment) {

        this.apiKey = environment.getProperty("tmdb.apiKey");
    }


    public MovieDTO getMovieDetails(Long movieId) {
        return tmdbService.getMovieDetails(movieId, apiKey);
    }
}
