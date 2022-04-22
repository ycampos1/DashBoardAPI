package campos.yennifer.domain.movies.services;

import campos.yennifer.domain.movies.model.Movies;

import java.util.List;
import java.util.Optional;

public interface MoviesService {

    Optional<List<Movies>> requestDataFromApi();
}
