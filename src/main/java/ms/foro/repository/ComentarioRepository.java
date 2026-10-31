package ms.foro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ms.foro.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTemaIdOrderByFechaCreacionAsc(Long temaId);
}