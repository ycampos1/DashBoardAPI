package campos.yennifer.domain.movies.repos;

import campos.yennifer.domain.movies.model.Movies;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepo extends CrudRepository<Movies, Long> {

}
