package hv.bd.shop.dao.entityservices;

import hv.bd.shop.ShopApplication;
import hv.bd.shop.controller.filter.BookFilter;
import hv.bd.shop.dao.entity.Book;
import hv.bd.shop.dao.entity.Product;
import hv.bd.shop.dao.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.stream.Collectors;

import static hv.bd.shop.controller.BookSpecification.byFilter;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(ShopApplication.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    public List<Book> getAllBooksByAuthorsName(String authorsName) {
        return bookRepository.findAllByAuthorsname(authorsName);
    }

    public List<Book> getBooksBy(BookFilter filter){
        Specification<Book> specification = where(byFilter(filter));
        return bookRepository.findAll(specification);
    }

    @Transactional
    public Book createProduct(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    @CacheEvict(value = "book", key = "#book.id")
    @Lock(value = LockModeType.READ)
    public Book update(Book book){
        try{
            return bookRepository.save(book);
        }catch(ObjectOptimisticLockingFailureException e){
            log.warn("Optimistic {}",book.getId());
            throw e;
        }
    }

    public Boolean delete(Long id){
        bookRepository.delete(bookRepository.getById(id));
        return true;
    }
}
