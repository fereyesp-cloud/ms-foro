package ms.foro.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.foro.client.UsuarioClient;
import ms.foro.dto.*;
import ms.foro.model.Categoria;
import ms.foro.model.Comentario;
import ms.foro.model.Tema;
import ms.foro.repository.CategoriaRepository;
import ms.foro.repository.ComentarioRepository;
import ms.foro.repository.TemaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemaService {

    private final TemaRepository temaRepository;
    private final ComentarioRepository comentarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioClient usuarioClient;

    // POST: publicar tema
    public TemaResponseDTO crear(TemaRequestDTO dto) {
        validarAutor(dto.getAutorId());

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));

        Tema tema = new Tema();
        tema.setTitulo(dto.getTitulo());
        tema.setContenido(dto.getContenido());
        tema.setAutorId(dto.getAutorId());
        tema.setCategoria(categoria);

        Tema guardado = temaRepository.save(tema);
        log.info("Tema {} publicado por usuario {}", guardado.getId(), dto.getAutorId());
        return TemaResponseDTO.desdeEntidad(guardado);
    }

    // GET: buscar temas (por categoría, por texto, o todos)
    public List<TemaResponseDTO> buscar(Long categoriaId, String texto) {
        log.info("Buscando temas: categoria={}, texto={}", categoriaId, texto);

        List<Tema> temas;

        if (categoriaId != null) {
            temas = temaRepository.findByCategoriaId(categoriaId);
        } else if (texto != null && !texto.isBlank()) {
            temas = temaRepository.findByTituloContainingIgnoreCase(texto);
        } else {
            temas = temaRepository.findAll();
        }

        log.debug("Temas encontrados: {}", temas.size());
        return temas.stream()
            .map(TemaResponseDTO::desdeEntidad)
            .toList();
    }

    // GET: ver un tema con sus comentarios
    public TemaDetalleDTO obtenerDetalle(Long id) {
        log.info("Buscando tema por id {}", id);

        Tema tema = temaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Tema no encontrado con id {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema no encontrado");
                });

        List<ComentarioResponseDTO> comentarios = comentarioRepository
            .findByTemaIdOrderByFechaCreacionAsc(tema.getId())
            .stream()
            .map(ComentarioResponseDTO::desdeEntidad)
            .toList();

        log.debug("Tema {} con {} comentarios", tema.getId(), comentarios.size());
        return new TemaDetalleDTO(TemaResponseDTO.desdeEntidad(tema), comentarios);
    }

    // POST: comentar un tema
    public ComentarioResponseDTO comentar(Long temaId, ComentarioRequestDTO dto) {
        log.info("Creando comentario en el tema {}", temaId);

        Tema tema = temaRepository.findById(temaId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema no encontrado"));

        validarAutor(dto.getAutorId());

        Comentario comentario = new Comentario();
        comentario.setContenido(dto.getContenido());
        comentario.setAutorId(dto.getAutorId());
        comentario.setTema(tema);

        Comentario guardado = comentarioRepository.save(comentario);
        log.info("Comentario {} creado en el tema {} por el usuario {}", guardado.getId(), temaId, dto.getAutorId());
        return ComentarioResponseDTO.desdeEntidad(guardado);
    }

    // Verifica en ms-usuarios que el autor exista y esté activo
    private UsuarioDTO validarAutor(Long autorId) {
        UsuarioDTO usuario = usuarioClient.obtenerUsuario(autorId);
        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario está desactivado");
        }
        return usuario;
    }
}