package prueba.banco.service;

import java.util.List;

import prueba.banco.entity.Movimiento;

public interface MovimientoService {
    Movimiento saveMovimiento(Movimiento movimiento);
    List<Movimiento> getMovimientoCuenta(Integer idCuenta);
    Movimiento updateMovimiento(Movimiento movimiento);
    void deleteMovimiento(Integer idMovimiento);
}
