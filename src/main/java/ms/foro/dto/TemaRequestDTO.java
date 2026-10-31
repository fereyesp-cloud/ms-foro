package ms.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TemaRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 4000, message = "El contenido no puede superar los 4000 caracteres")
    private String contenido;

    @NotNull(message = "El autor es obligatorio")
    private Long autorId;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;
}
