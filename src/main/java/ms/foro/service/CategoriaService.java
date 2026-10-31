package ms.foro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.foro.model.Categoria;
import ms.foro.repository.CategoriaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // GET: listar categorías
    public List<Categoria> listar() {
        log.info("Listando categorías");

        List<Categoria> categorias = categoriaRepository.findAll();
        log.debug("Categorías encontradas: {}", categorias.size());

        return categorias;
    }
}