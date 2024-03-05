package mx.edu.utez.libros.controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.libros.model.Book;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {

    private Long id;

    private String nombre;
    private String autor;
    private String portada;
    private Timestamp fechaPublicacion;

    public Book getBooks(){
        return new Book(
                getId(),
                getNombre(),
                getAutor(),
                getPortada(),
                getFechaPublicacion()
        );
    }
}
