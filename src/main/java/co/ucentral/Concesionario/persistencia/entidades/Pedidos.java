package co.ucentral.Concesionario.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false, foreignKey = @ForeignKey(name = "fk_vehiculo"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehiculo vehiculo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;


    @Column(name = "estado", nullable = false)
    private String estado = "Pendiente";
}
