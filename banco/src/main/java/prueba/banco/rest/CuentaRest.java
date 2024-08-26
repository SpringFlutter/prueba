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

import prueba.banco.entity.Cuenta;
import prueba.banco.service.CuentaService;

@RestController
@RequestMapping("/v1/banco")

public class CuentaRest {
    
    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/cuentas")
    public ResponseEntity<Cuenta> saveCuenta(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.saveCuenta(cuenta) , HttpStatus.CREATED);
    }

    @GetMapping("/cuentas/{idCliente}")
    public ResponseEntity<List<Cuenta>> getCuentaCliente(@PathVariable Long idCliente) {
        return new ResponseEntity<>(cuentaService.getCuentaCliente(idCliente), HttpStatus.OK);
    }
    
    @PutMapping("/cuentas")
    public ResponseEntity<Cuenta> updateCuenta(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.updateCuenta(cuenta), HttpStatus.OK);
    }

    @DeleteMapping("/cuentas/{idCuenta}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer idCuenta) {
        cuentaService.deleteCuenta(idCuenta);
        return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);
    }
}
