package mx.edu.utez.libros.repository;

import mx.edu.utez.libros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findAllByOrderByAutorAsc();
    List<Book> findAllByOrderByAutorDesc();

    @Query(
            value = "SELECT * FROM books  WHERE nombre LIKE %:nombre%",
            nativeQuery = true
    )
    List<Book> findBookByNombre(String nombre);

    List<Book> findAllByOrderByFechaPublicacionAsc();
    List<Book> findAllByOrderByFechaPublicacionDesc();

    @Modifying
    @Query(
            value = "DELETE FROM books WHERE id = :id",
            nativeQuery = true
    )
    void deleteById(Long id);
}
