package mx.edu.utez.libros.service;


import mx.edu.utez.libros.controller.Dto.BookDto;
import mx.edu.utez.libros.model.Book;
import mx.edu.utez.libros.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class BookService {


    @Autowired
    private BookRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Book>> getAllBooks() {
       return new Response<>(
               this.repository.findAll(),
               false,
               200,
               "OK!"
       );
    }
    @Transactional(rollbackFor = SQLException.class)
    public Response<List<Book>> getOneBook(String nombre) {
        if (nombre == null){
            return new Response<>(
                    null,
                    true,
                    400,
                    "EL LIBRO NO EXISTE"
            );
        }

       return new Response<>(
               this.repository.findBookByNombre(nombre),
               false,
               200,
               "LIBRO ENCONTRADO"
       );

    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<List<Book>> getAutorAsc() {
        List<Book> book;
        book = this.repository.findAllByOrderByAutorAsc();
        return new Response<>(
                book,
                false,
                200,
                "AUTORES DE FORMA ASCENDENTE"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<List<Book>> getAutorDesc() {
        List<Book> book;
        book = this.repository.findAllByOrderByAutorDesc();
        return new Response<>(
                book,
                false,
                200,
                "AUTORES DE FORMA DESCENDENTE"
        );
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Response<List<Book>> getFechasAsc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByFechaPublicacionAsc();
        return new Response<>(
                libro,
                false,
                200,
                "FECHAS DE FORMA ASCENDENTE"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<List<Book>> getFechasDesc() {
        List<Book> libro;
        libro = this.repository.findAllByOrderByFechaPublicacionDesc();
        return new Response<>(
                libro,
                false,
                200,
                "FECHAS DE FORMA DESCENDENTE"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Book> insertBook(BookDto book) {
        return new Response<>(
            this.repository.saveAndFlush(book.getBooks()),
            false,
            200,
            "LIBRO GUARDADO CORRECTAMENTE"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Book> deleteBookById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "LIBRO ELIMINADO"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "NO SE ENCONTRO EL LIBRO"
        );
    }

    public Response<Book> updateBook(Book book) {
        return new Response(
                this.repository.saveAndFlush(book),
                false,
                200,
                "LIBRO ACTALIZADO"
        );
    }

}
