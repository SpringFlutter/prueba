package prueba.banco.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prueba.banco.enumerations.TipoGenero;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor


public class Persona implements Serializable {

    @Size(max = 200,  message = "El nombre no debe exceder los 200 caracteres")
    @Column(name = "nombre", nullable = false, length = 200, updatable = false)
    private String nombre;


    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private TipoGenero genero;

    @Column(name = "fecha_nacimiento", nullable = false, updatable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "edad")
    private String edad;

    @Size(min = 10,  message = "La identificacion debe tener 10 digitos")
    @Column(name = "identificacion", nullable = false, length = 10, updatable = false)
    private String identificacion;

    @Size(max = 200,  message = "La direccion debe exceder los 200 caracteres")
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Size(min = 10,  message = "El celular debe tener 10 digitos")
    @Column(name = "celular", nullable = false, length = 10)
    private String celular;
}
