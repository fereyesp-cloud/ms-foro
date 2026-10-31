package ms.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class ComentarioRequestDTO {
    
    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 2000, message = "El contenido no puede superar los 2000 caracteres")
    private String contenido;

    @NotNull(message = "El autor es obligatorio")
    private Long autorId;
}
