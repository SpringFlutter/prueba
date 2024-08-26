package prueba.banco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import prueba.banco.entity.Movimiento;
import prueba.banco.service.MovimientoService;

@RestController
@RequestMapping("/v1/banco")


public class MovimientoRest {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/movimientos")
    public ResponseEntity<Movimiento> saveMovimiento(@RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(movimientoService.saveMovimiento(movimiento) , HttpStatus.CREATED);
    }

    @GetMapping("/movimientos/{idCuenta}")
    public ResponseEntity<List<Movimiento>> getCuentaMovimientos(@PathVariable Integer idCuenta) {
        return new ResponseEntity<>(movimientoService.getMovimientoCuenta(idCuenta), HttpStatus.OK);
    }
    
    @PutMapping("/movimientos")
    public ResponseEntity<Movimiento> updateMovimiento(@RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(movimientoService.updateMovimiento(movimiento), HttpStatus.OK);
    }

    @DeleteMapping("/cuentas/{idMovimiento}")
    public ResponseEntity<String> deleteMovimiento(@PathVariable Integer idMovimiento) {
        movimientoService.deleteMovimiento(idMovimiento);
        return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);
    }

}
