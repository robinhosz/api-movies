package br.com.movieproject.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class MovieDTO {

    private Long id;
    private String title;
    private String overview;
    private String poster_path;
    private Double vote_average;

}