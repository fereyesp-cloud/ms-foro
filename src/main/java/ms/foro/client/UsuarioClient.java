package ms.foro.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.foro.dto.UsuarioDTO;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final RestClient usuariosRestClient;

    public UsuarioDTO obtenerUsuario(Long id) {
        log.debug("Consultando usuario {} en ms-usuarios", id);
        try {
            return usuariosRestClient.get()
                    .uri("/api/usuarios/{id}", id)
                    .retrieve()
                    .body(UsuarioDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario " + id + " no existe");
        } catch (ResourceAccessException e) {
            log.error("No se pudo conectar con ms-usuarios", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "El servicio de usuarios no está disponible");
        }
    }
}