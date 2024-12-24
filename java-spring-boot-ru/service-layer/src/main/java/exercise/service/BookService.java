package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::map).toList();
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found!"));
        return bookMapper.map(book);
    }

    public BookDTO create(BookCreateDTO dto) {
        var book = bookMapper.map(dto);
        if (!authorRepository.existsById(dto.getAuthorId())) {
            throw new ConstraintViolationException("Author with id " + dto.getAuthorId() + " not found!", null);
        }
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO dto, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found!"));

        if (!authorRepository.existsById(dto.getAuthorId().get())) {
            throw new ConstraintViolationException("Author with id " + dto.getAuthorId().get() + " not found!", null);
        }

        bookMapper.update(dto, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void destroy(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
