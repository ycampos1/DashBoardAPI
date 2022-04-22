package campos.yennifer.domain.books.services;

import campos.yennifer.domain.books.exception.BookNotFoundException;
import campos.yennifer.domain.books.model.Book;
import campos.yennifer.domain.books.repos.BooksRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class BooksServiceImpl implements BooksServices {
    private Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);
    private RestTemplate restTemplate;
    private BooksRepo booksRepo;

    @Autowired
    public BooksServiceImpl(BooksRepo booksRepo) {
        this.restTemplate = new RestTemplate();
        this.booksRepo = booksRepo;
    }

    @Value("${book.api.host}")
    private String host;

    @Value("${book.api.key}")
    private String key;

//    @PostConstruct()
//    public void setUp() {
//        requestDataFromApi("56597885");
//    }

    @Override
    public Book requestBook(String book_id) throws BookNotFoundException {
        Optional<Book> optional = requestDataFromApi(book_id);
        if (optional.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return optional.get();
    }



    @Override
    public Book save(Book book) {
        return booksRepo.save(book);
    }

   private Optional<Book> requestDataFromApi(String book_id) {
        try {
            String url = "https://hapi-books.p.rapidapi.com/book/%s";
            String requestUrl = String.format(url, book_id);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Host", host);
            httpHeaders.set("X-RapidAPI-Key", key);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<Book> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Book.class);
            Book book = response.getBody();
            log.info(book.toString());
            book = save(book);
            return Optional.of(book);
        } catch (HttpClientErrorException ex){
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }
}


