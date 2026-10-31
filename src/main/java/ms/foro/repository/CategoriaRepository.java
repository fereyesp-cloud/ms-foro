package ms.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.foro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);
}