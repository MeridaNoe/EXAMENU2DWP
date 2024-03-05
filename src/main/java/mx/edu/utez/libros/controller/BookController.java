package mx.edu.utez.libros.controller;

import mx.edu.utez.libros.controller.Dto.BookDto;
import mx.edu.utez.libros.model.Book;
import mx.edu.utez.libros.service.BookService;
import mx.edu.utez.libros.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new  ResponseEntity(
                this.service.getAllBooks(),
                HttpStatus.OK
        );
    }

    @GetMapping("/oneBook")
    public ResponseEntity<Response<List<Book>>> getBook(@RequestParam String nombre) {
        return new ResponseEntity<>(
                this.service.getOneBook(nombre),
                HttpStatus.OK
        );
    }

    @GetMapping("/autorAsc")
    public ResponseEntity<List<Book>> getAutorAsc() {
        return new ResponseEntity(
                this.service.getAutorAsc(),
                HttpStatus.OK);
    }
    @GetMapping("/autorDesc")
    public ResponseEntity<List<Book>> getAutorDesc() {
        return new ResponseEntity(
                this.service.getAutorDesc(),
                HttpStatus.OK);
    }

    @GetMapping("/fechaAsc")
    public ResponseEntity<List<Book>> wtihPhotocover() {
        return new ResponseEntity(
                this.service.getFechasAsc(),
                HttpStatus.OK);
    }

    @GetMapping("/fechaDesc")
    public ResponseEntity<List<Book>> finbyDateDesc() {
        return new ResponseEntity(
                this.service.getFechasDesc(),
                HttpStatus.OK
        );
    }


    @PostMapping("/")
    public ResponseEntity<Response<Book>> insertBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(
                this.service.insertBook(bookDto),
                HttpStatus.CREATED
        );
    }


    @PutMapping("/update")
    public ResponseEntity<Response<Book>> updateBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(
                this.service.updateBook(bookDto.getBooks()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Book>> deleteBook(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.deleteBookById(id),
                HttpStatus.OK
        );
    }
}
