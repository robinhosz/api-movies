package br.com.movieproject.proxy;

import br.com.movieproject.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tmdb", url = "https://api.themoviedb.org/3")
public interface TmdbService {

    @GetMapping("/movie/{movieId}")
    MovieDTO getMovieDetails(@PathVariable("movieId") Long movieId, @RequestParam("api_key") String apiKey);

}
