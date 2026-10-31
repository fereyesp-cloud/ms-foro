package ms.foro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ms.foro.model.Categoria;
import ms.foro.repository.CategoriaRepository;
import ms.foro.service.CategoriaService;

@RestController 
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    // GET: listar categorías
    @GetMapping
        public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }
}
