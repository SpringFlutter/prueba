package prueba.banco.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.banco.entity.Cliente;
import prueba.banco.projections.CuentaMovimiento;
import prueba.banco.service.ClienteService;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/v1/banco/")

public class ClienteRest {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.saveCliente(cliente) , HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getClientes() {
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);
    }
    
    @PutMapping("/clientes")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.updateCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{identificacion}")
    public ResponseEntity<String> deleteCliente(@PathVariable String identificacion) {
        return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
    }

    @GetMapping("/clientes/detalle/{identificacion}/{fechaInicial}/{fechaFinal}")
    public ResponseEntity<List<CuentaMovimiento>> getClienteDetalles(@PathVariable String identificacion,
        @PathVariable LocalDate fechaInicial, @PathVariable LocalDate fechaFinal) {
        return new ResponseEntity<>
        (clienteService.getCuentaMovimientos(identificacion, fechaInicial, fechaFinal), HttpStatus.OK);
    }
    
}
