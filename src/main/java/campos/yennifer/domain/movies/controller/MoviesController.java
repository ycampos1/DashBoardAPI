package campos.yennifer.domain.movies.controller;

import campos.yennifer.domain.movies.model.Movies;
import campos.yennifer.domain.movies.services.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("movies")
public class MoviesController {
    private static Logger logger = LoggerFactory.getLogger(MoviesController.class);
    private MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("")
    public ResponseEntity<List<Movies>> requestMovies(){
        Optional<List<Movies>> response = moviesService.requestDataFromApi();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
