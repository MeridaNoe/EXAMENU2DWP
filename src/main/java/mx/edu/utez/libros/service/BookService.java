package mx.edu.utez.libros.service;


import mx.edu.utez.libros.controller.Dto.BookDto;
import mx.edu.utez.libros.model.Book;
import mx.edu.utez.libros.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {


    @Autowired
    private BookRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAllBooks() {
       return new CustomResponse<>(
               this.repository.findAll(),
               false,
               200,
               "OK!"
       );
    }
    @Transactional(rollbackFor = SQLException.class)
    public CustomResponse<List<Book>> getOneBook(String nombre) {
        if (nombre == null){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "EL LIBRO NO EXISTE"
            );
        }

       return new CustomResponse<>(
               this.repository.findBookByNombre(nombre),
               false,
               200,
               "LIBRO ENCONTRADO"
       );

    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> getAutorAsc() {
        List<Book> book;
        book = this.repository.findAllByOrderByAutorAsc();
        return new CustomResponse<>(
                book,
                false,
                200,
                "Autores Ascedente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> getAutorDesc() {
        List<Book> book;
        book = this.repository.findAllByOrderByAutorDesc();
        return new CustomResponse<>(
                book,
                false,
                200,
                "Autores Descendente"
        );
    }


    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> getFechasAsc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByFechaPublicacionAsc();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Fechas Ascendente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Book>> getFechasDesc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByFechaPublicacionDesc();
        return new CustomResponse<>(
                libro,
                false,
                200,
                "Fechas Descendente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Book> insertBook(BookDto book) {
        return new CustomResponse<>(
            this.repository.saveAndFlush(book.getBooks()),
            false,
            200,
            "LIBRO GUARDADO CORRECTAMENTE"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public CustomResponse<Book> deleteBookById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return new CustomResponse<>(
                    null,
                    false,
                    200,
                    "LIBRO ELIMINADO"
            );
        }
        return new CustomResponse<>(
                null,
                true,
                400,
                "NO SE ENCONTRO EL LIBRO"
        );
    }

    public CustomResponse<Book> updateBook(Book book) {
        return new CustomResponse(
                this.repository.saveAndFlush(book),
                false,
                200,
                "LIBRO ACTALIZADO"
        );
    }

}
