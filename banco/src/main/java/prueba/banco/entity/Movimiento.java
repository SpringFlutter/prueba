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

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prueba.banco.enumerations.TipoMovimiento;

@Entity
@Table(name = "tbl_movimiento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Movimiento {
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_movimiento")
    @SequenceGenerator(name = "sec_movimiento", sequenceName = "sec_movimiento", allocationSize = 1)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento = LocalDate.now();

    @Column(name = "hora_movimiento")
    private LocalTime horaMovimiento = LocalTime.now().withNano(0);

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "saldo", nullable = false)
    private Double saldo;
}
