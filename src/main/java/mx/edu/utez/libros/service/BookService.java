package mx.edu.utez.libros.service;


import mx.edu.utez.libros.model.Book;
import mx.edu.utez.libros.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> getBookByNombre(String nombre){
        return repository.findBookByNombre(nombre);
    }
    public List<Book> getBookByAuthor(String autor) {
        return repository.findBookByAutor(autor);
    }

    public Book buscarByFechaPublicacion(Date fechaPublicacion){
        return repository.findBookByFechaPublicacion(fechaPublicacion);
    }

    public Book insertBook(Book book) {
        return repository.save(book);
    }

    public void deletePeli(Long id) {
        repository.deleteById(id);
    }

    public Book updateBook(Book book) {
        return repository.saveAndFlush(book);
    }

}
