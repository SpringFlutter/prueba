package prueba.banco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import prueba.banco.enumerations.TipoCuenta;

@Entity
@Table(name = "tbl_cuenta")
@Getter 
@Setter

public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_cuenta")
    @SequenceGenerator(name = "sec_cuenta", sequenceName = "sec_cuenta", allocationSize = 1)
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "numero_cuenta", nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(name = "estado")
    private Boolean estado = true;
}
