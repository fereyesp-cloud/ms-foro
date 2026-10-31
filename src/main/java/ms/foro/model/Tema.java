package ms.foro.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TEMAS")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_temas")
    @SequenceGenerator(name = "seq_temas", sequenceName = "SEQ_TEMAS", allocationSize = 1)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "contenido", nullable = false, length = 4000)
    private String contenido;

    @Column(name = "autor_id", nullable = false)
    private Long autorId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}