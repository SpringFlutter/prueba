package prueba.banco.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prueba.banco.model.Persona;

@Entity
@Table(name = "tbl_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_cliente")
    @SequenceGenerator(name = "sec_cliente", sequenceName = "sec_cliente", allocationSize = 1)
    @Column(name = "id_cliente", nullable = false, updatable = false)
    private Long idCliente;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    // @OneToMany(mappedBy = "idCliente")
    // private List<Cuenta> cuentas;
}
