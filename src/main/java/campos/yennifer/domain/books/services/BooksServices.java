package campos.yennifer.domain.books.services;

import campos.yennifer.domain.books.exception.BookNotFoundException;
import campos.yennifer.domain.books.model.Book;

import java.util.Optional;

public interface BooksServices {

    Book requestBook(String book_id) throws BookNotFoundException;

    Book save(Book book);
}
