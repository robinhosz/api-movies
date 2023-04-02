package br.com.movieproject.model;

import lombok.Data;
import lombok.ToString;

@Data
public class MovieDTO {

    private String title;
    private String overview;
    private String poster_path;
    private Double vote_average;

}