package ms.foro.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.foro.model.Categoria;
import ms.foro.repository.CategoriaRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class CargaCategorias implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) {
        crearSiNoExiste("Tecnología", "Programación, hardware, software y novedades tecnológicas");
        crearSiNoExiste("Juegos", "Lanzamientos, actualizaciones y novedades de los juegos más populares");
        crearSiNoExiste("TCG", "Nuevas expansiones de los juegos de cartas coleccionables más populares");
        crearSiNoExiste("Móvil", "Información y lanzamientos de los celulares de última generación");
        crearSiNoExiste("Política", "Novedades de la política nacional e internacional");
    }

    private void crearSiNoExiste(String nombre, String descripcion) {
        if (!categoriaRepository.existsByNombre(nombre)) {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            categoriaRepository.save(categoria);
            log.info("Categoría creada: {}", nombre);
        }
    }
}