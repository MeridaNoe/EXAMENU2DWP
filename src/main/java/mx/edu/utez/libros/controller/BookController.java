package mx.edu.utez.libros.controller;

import mx.edu.utez.libros.controller.Dto.BookDto;
import mx.edu.utez.libros.model.Book;
import mx.edu.utez.libros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/oneBook")
    public List<Book> getBook(@RequestParam String nombre) {
        return service.getBookByNombre(nombre);
    }

    @GetMapping("/autor")
    public List<Book> getBooksByAuthor(@RequestParam String autor){
        return service.getBookByAuthor(autor);
    }

    @GetMapping("/buscarFecha")
    public  Book getBookByFecha(@RequestParam String fechaPublicacion) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = format.parse(fechaPublicacion);
        System.out.println("FECHA DE PUBLICACION: " +  fechaPublicacion);

        return service.buscarByFechaPublicacion(fecha);

    }

    @PostMapping("/")
    public Book insertBook(@RequestBody BookDto bookDto){
        return service.insertBook(bookDto.getBooks());
    }


    @PutMapping("/update")
    public Book updateBook(@RequestBody BookDto bookDto){
        return service.updateBook(bookDto.getBooks());
    }

    @DeleteMapping("/delete")
    public void deletePeliculas(@RequestParam Long id){
        service.deletePeli(id);
    }
}
