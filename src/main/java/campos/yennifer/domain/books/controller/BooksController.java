package campos.yennifer.domain.books.controller;

import campos.yennifer.domain.books.exception.BookNotFoundException;
import campos.yennifer.domain.books.model.Book;
import campos.yennifer.domain.books.services.BooksServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin("http://localhost:3000")
@RequestMapping("book")
public class BooksController {

    private static Logger logger = LoggerFactory.getLogger(BooksController.class);
    private BooksServices booksService;

    @Autowired
    public BooksController(BooksServices booksService) {
        this.booksService = booksService;
    }

    @GetMapping("")
    public ResponseEntity<Book> requestBook(@RequestParam(name="book_id", required = false) String book_id) throws BookNotFoundException {
        Book response = booksService.requestBook(book_id);
        log.info(response.toString());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
