package ms.foro.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data 
@Entity 
@Table (name = "COMENTARIOS")
public class Comentario {
    

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentarios")
    @SequenceGenerator(name = "seq_comentarios", sequenceName = "SEQ_AS", allocationSize = 1) 
    private Long id;

    @Column(name = "contenido", nullable = false, length = 2000)
    private String contenido;

    @Column(name = "autor_id", nullable = false)
    private Long autorId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "tema_id", nullable = false)
    private Tema tema;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
