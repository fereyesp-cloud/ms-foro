package ms.foro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ms.foro.service.TemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ms.foro.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/temas")
@CrossOrigin(origins = "*")
public class TemaController {
    
    private  final TemaService  temaService;

    public TemaController(TemaService temaService){
        this.temaService = temaService;
    }

    // GET: buscar temas (por categoría, texto o todos)
    @GetMapping
    public ResponseEntity<List<TemaResponseDTO>> buscar(
        @RequestParam(required = false) Long categoriaId,
        @RequestParam(required = false) String texto) {
        return ResponseEntity.ok(temaService.buscar(categoriaId, texto));
    }


    // GET: obtener un tema con sus comentarios
    @GetMapping("/{id}")
    public ResponseEntity<TemaDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(temaService.obtenerDetalle(id));
    }

    // POST: crear tema
    @PostMapping
    public ResponseEntity<TemaResponseDTO> crear(@Valid @RequestBody TemaRequestDTO dto) {
        TemaResponseDTO response = temaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
        
    // POST: comentar un tema
    @PostMapping("/{id}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> comentar(@PathVariable Long id,
                                                      @Valid @RequestBody ComentarioRequestDTO dto) {
        ComentarioResponseDTO response = temaService.comentar(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    

}
