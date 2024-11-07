package co.ucentral.Concesionario.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "veh_marca", nullable = false)
    private String marca;

    @Column(name = "veh_modelo", nullable = false)
    private String modelo;

    @Column(name = "veh_ano", nullable = false)
    private String ano;

    @Column(name = "veh_precio", nullable = false)
    private String precio;

}
