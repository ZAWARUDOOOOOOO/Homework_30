package hv.bd.shop.controller;


import hv.bd.shop.controller.filter.BookFilter;
import hv.bd.shop.controller.view.BookView;
import hv.bd.shop.controller.view.mapper.BookViewMapper;
import hv.bd.shop.dao.entity.Book;
import hv.bd.shop.dao.entityservices.AuthorService;
import hv.bd.shop.dao.entityservices.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//http://localhost:8080/api/v1/book
@Slf4j
@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookViewMapper mapper;
    private final AuthorService authorService;

    public BookController(BookService bookService, BookViewMapper mapper, AuthorService authorService) {
        this.bookService = bookService;
        this.mapper = mapper;
        this.authorService = authorService;
    }

    @GetMapping
    public List<BookView> getBooks(@RequestParam(value = "minCostBound", required = false) Long minCostBound,
                                   @RequestParam(value = "maxCostBound", required = false) Long maxCostBound) {
        return bookService.getBooksBy(new BookFilter(minCostBound, maxCostBound))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/bookId")
    public BookView getBook(@PathVariable("bookId") Long bookId) {
        return mapper.mapToView(bookService.getBookById(bookId));
    }

    @PostMapping
    public BookView createBook(@RequestBody BookView body) {
        if (body.getId() != null) {//нужна проверка на боди при создании сущности
            throw new EntityExistsException(String
                    .format("Book with id = %s already exists", body.getId()));
        }
        Book book = mapper.mapFromView(body);
        Book createdBook = bookService.createBook(book);
        return mapper.mapToView(createdBook);
    }

    @PutMapping("/{bookId}")
    public BookView updateBook(@PathVariable("bookId") Long bookId,
                               @RequestBody BookView body) {
        if (body.getId() == null) {//нужна проверка на боди при создании сущности
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(bookId, body.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Book book = bookService.getBookById(bookId);

        if (book.getCost() != body.getCost()) {
            book.setCost(body.getCost());
        }

        Book updated = bookService.update(book);
        return mapper.mapToView(updated);
    }

    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Long bookId) {
        return bookService.delete(bookId);
    }
}
