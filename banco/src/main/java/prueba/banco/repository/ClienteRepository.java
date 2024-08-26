package prueba.banco.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import prueba.banco.entity.Cliente;
import prueba.banco.projections.CuentaMovimiento;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByOrderByNombre();
    Optional<Cliente> findByIdentificacion(String identificacion);
    void deleteByIdentificacion(String identificacion);

    @Query(value = "SELECT edad from tbl_cliente where id_cliente = :idCliente", nativeQuery = true)
    String queryGetEdad(@Param("idCliente")Long idCliente);

    @Query(value = "SELECT fecha_movimiento as fechaMovimiento,\n"+
    "hora_movimiento as horaMovimiento,\n"+
    "nombre, identificacion, numero_cuenta as numeroCuenta,\n"+
    "tipo_cuenta as tipoCuenta, saldo_inicial as saldoInicial,\n"+
    "estado, tipo_movimiento as tipoMovimiento, movimiento,\n"+
    "saldo_disponible as saldoDisponible\n"+
    "FROM vta_cuenta_movimiento\n"+
    "WHERE identificacion = :identificacion and fecha_movimiento between :fechaInicial and :fechaFinal", nativeQuery = true)
    List<CuentaMovimiento> queryGetCuentaMovimientos(@Param("identificacion")String identificacion,
    @Param("fechaInicial")LocalDate fechaIncial, @Param("fechaFinal")LocalDate fechaFinal);
}
