package campos.yennifer.domain.movies.services;

import campos.yennifer.domain.movies.model.Movies;
import campos.yennifer.domain.movies.repos.MoviesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImpl implements MoviesService {
    private Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);
    private RestTemplate restTemplate;
    private MoviesRepo moviesRepo;

    @Value("${movies.api.host}")
    private String host;

    @Value("${movies.api.key}")
    private String key;

    @Autowired
    public MoviesServiceImpl(MoviesRepo moviesRepo) {
        this.moviesRepo=moviesRepo;
        this.restTemplate = new RestTemplate();
    }
    @PostConstruct()
    public void setUp(){
        requestDataFromApi();
    }

    @Override
    public Optional<List<Movies>> requestDataFromApi() {
        try {
            String url = "https://latest-movies.p.rapidapi.com/movies";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Host", host);
            httpHeaders.set("X-RapidAPI-Key", key);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<ArrayList<Movies>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
            });
            ArrayList<Movies> movies = response.getBody();
            logger.info(movies.get(1).toString());
            return Optional.of(movies);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }
    }
}
