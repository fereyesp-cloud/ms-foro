package ms.foro.dto;

import java.time.LocalDateTime;

import lombok.Data;
import ms.foro.model.Comentario;

@Data
public class ComentarioResponseDTO {

    private Long id;
    private String contenido;
    private Long autorId;
    private LocalDateTime fechaCreacion;

    public static ComentarioResponseDTO desdeEntidad(Comentario comentario) {
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setId(comentario.getId());
        dto.setContenido(comentario.getContenido());
        dto.setAutorId(comentario.getAutorId());
        dto.setFechaCreacion(comentario.getFechaCreacion());
        return dto;
    }
}