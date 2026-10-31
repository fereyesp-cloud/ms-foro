package ms.foro.dto;

import java.time.LocalDateTime;

import lombok.Data;
import ms.foro.model.Tema;

@Data 
public class TemaResponseDTO {
    
    private Long id;
    private String titulo;
    private String contenido;
    private Long autorId;
    private LocalDateTime fechaCreacion;
    private Long categoriaId;
    private String categoriaNombre;

    public static TemaResponseDTO desdeEntidad(Tema tema){

        TemaResponseDTO dto = new TemaResponseDTO();
        dto.setId(tema.getId());
        dto.setTitulo(tema.getTitulo());
        dto.setContenido(tema.getContenido());
        dto.setAutorId(tema.getAutorId());
        dto.setFechaCreacion(tema.getFechaCreacion());
        dto.setCategoriaId(tema.getCategoria().getId());
        dto.setCategoriaNombre(tema.getCategoria().getNombre());
        return dto;

    }
}
