package mx.edu.utez.libros.repository;

import mx.edu.utez.libros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query(
            value = "SELECT * FROM books  WHERE autor LIKE %:autor%",
            nativeQuery = true
    )
    List<Book> findBookByAutor(String autor);

    @Query(
            value = "SELECT * FROM books  WHERE nombre LIKE %:nombre%",
            nativeQuery = true
    )
    List<Book> findBookByNombre(String nombre);

    Book findBookByFechaPublicacion(Date fechaPublicacion);
}
