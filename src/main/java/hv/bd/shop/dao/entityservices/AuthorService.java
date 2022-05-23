package hv.bd.shop.dao.entityservices;

import hv.bd.shop.dao.entity.Author;
import hv.bd.shop.dao.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.getById(id);
    }

    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}
