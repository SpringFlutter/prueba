package prueba.banco.service;

import java.util.List;

import prueba.banco.entity.Cuenta;

public interface CuentaService {
    Cuenta saveCuenta(Cuenta cuenta);
    List<Cuenta> getCuentaCliente(Long idCliente);
    Cuenta updateCuenta(Cuenta cuenta);
    void deleteCuenta(Integer idCuenta);
}
