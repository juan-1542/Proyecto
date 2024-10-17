package co.ucentral.Concesionario.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "usuarios")
@Table
public class Usuario {
    @Id
    @Column(name = "usu_nombre")
    public String nombre;
    @Column(name = "usu_contraseña")
    public int contraseña;


}
