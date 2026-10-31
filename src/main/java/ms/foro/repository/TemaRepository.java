package ms.foro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.foro.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    List<Tema> findByCategoriaId(Long categoriaId);

    List<Tema> findByTituloContainingIgnoreCase(String texto);
}