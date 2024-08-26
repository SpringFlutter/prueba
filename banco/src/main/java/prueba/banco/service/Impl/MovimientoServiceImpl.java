package prueba.banco.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import prueba.banco.entity.Cuenta;
import prueba.banco.entity.Movimiento;
import prueba.banco.enumerations.TipoMovimiento;
import prueba.banco.repository.CuentaRepository;
import prueba.banco.repository.MovimientoRepository;
import prueba.banco.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Movimiento saveMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getIdCuenta())
            .orElseThrow( () ->
             new ResponseStatusException( HttpStatus.NOT_FOUND, "Cuenta id " + movimiento.getIdCuenta() + "no encontrada"));
        
        Double saldoInicial = cuenta.getSaldoInicial();
        Double nuevoSaldo;
     
        if (TipoMovimiento.DEPOSITO.equals(movimiento.getTipoMovimiento())) {
            nuevoSaldo = saldoInicial + movimiento.getValor();
        } else if (TipoMovimiento.RETIRO.equals(movimiento.getTipoMovimiento())) {
            if(saldoInicial == 0){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Saldo no disponible");
            }
            nuevoSaldo = saldoInicial - movimiento.getValor();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tipo de movimiento no soportado");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setSaldo(saldoInicial);
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> getMovimientoCuenta(Integer idCuenta) {
        return movimientoRepository.findByIdCuentaOrderByFechaMovimientoDescHoraMovimientoDesc(idCuenta);
    }

    @Override
    public Movimiento updateMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public void deleteMovimiento(Integer idMovimiento) {

        Movimiento movimiento = movimientoRepository.findById(idMovimiento)
            .orElseThrow( () ->
            new ResponseStatusException( HttpStatus.NOT_FOUND, "Movimiento id " + idMovimiento + "no encontrada"));
        
        Cuenta cuenta = cuentaRepository.findById(movimiento.getIdCuenta())
        .orElseThrow( () ->
         new ResponseStatusException( HttpStatus.NOT_FOUND, "Cuenta id " + movimiento.getIdCuenta() + "no encontrada"));
    
        Double saldoInicial = cuenta.getSaldoInicial();
        Double nuevoSaldo;
    
        if (TipoMovimiento.DEPOSITO.equals(movimiento.getTipoMovimiento())) {
            nuevoSaldo = saldoInicial - movimiento.getValor();
        } else if (TipoMovimiento.RETIRO.equals(movimiento.getTipoMovimiento())) {
            nuevoSaldo = saldoInicial + movimiento.getValor();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tipo de movimiento no soportado");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimientoRepository.deleteById(idMovimiento);
    }
    
}
