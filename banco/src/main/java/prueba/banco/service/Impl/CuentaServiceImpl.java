package prueba.banco.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import prueba.banco.entity.Cuenta;
import prueba.banco.repository.CuentaRepository;
import prueba.banco.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        Cuenta cuentaNueva = cuentaRepository.saveAndFlush(cuenta);
        cuentaNueva.setNumeroCuenta(cuentaRepository.queryByIdCuenta(cuentaNueva.getIdCuenta()));
        return cuentaNueva;
    }

    @Override
    public List<Cuenta> getCuentaCliente(Long idCliente) {
        return cuentaRepository.findByIdCliente(idCliente);
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        return cuentaRepository.findById(cuenta.getIdCuenta())
            .map(
                cuentaExiste ->{
                    cuentaExiste.setSaldoInicial(cuenta.getSaldoInicial());
                    cuentaExiste.setEstado(cuenta.getEstado());
                    return cuentaExiste;
                }
            )
            .map(cuentaRepository :: save)
            .orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta con id " + cuenta.getIdCuenta() + "no existe")
            );
    }

    @Override
    public void deleteCuenta(Integer idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }
    
}
