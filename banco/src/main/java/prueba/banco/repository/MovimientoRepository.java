package prueba.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import prueba.banco.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByIdCuentaOrderByFechaMovimientoDescHoraMovimientoDesc(Integer idCuenta);
}
