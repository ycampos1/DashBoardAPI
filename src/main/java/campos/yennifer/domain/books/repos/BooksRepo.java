package campos.yennifer.domain.books.repos;

import campos.yennifer.domain.books.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BooksRepo extends CrudRepository<Book, Long> {
    Optional<Book> findByBookId(String bookId);
}
