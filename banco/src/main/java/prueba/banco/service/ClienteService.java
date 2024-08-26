package prueba.banco.service;

import java.util.List;
import java.time.LocalDate;

import prueba.banco.entity.Cliente;
import prueba.banco.projections.CuentaMovimiento;

public interface ClienteService {
    Cliente saveCliente(Cliente cliente);
    List<Cliente> getClientes();
    Cliente updateCliente(Cliente cliente);
    void deleteCliente(String identificacion);
    List<CuentaMovimiento> getCuentaMovimientos(String identifiacion, LocalDate fechaInicial, LocalDate fechaFinal);
}
