package prueba.banco.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import prueba.banco.entity.Cliente;
import prueba.banco.projections.CuentaMovimiento;
import prueba.banco.repository.ClienteRepository;
import prueba.banco.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        clienteRepository.findByIdentificacion(cliente.getIdentificacion())
            .ifPresent(clienteExiste -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente ya existe");
            });

        Cliente nuevoCliente = clienteRepository.save(cliente);
        nuevoCliente.setEdad(clienteRepository.queryGetEdad(nuevoCliente.getIdCliente()));

        return nuevoCliente;
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findByOrderByNombre();
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.findByIdentificacion(cliente.getIdentificacion())
            .map(
                clienteExiste ->{
                    clienteExiste.setCelular(cliente.getCelular());
                    clienteExiste.setDireccion(cliente.getDireccion());
                    clienteExiste.setFechaNacimiento(cliente.getFechaNacimiento());
                    clienteExiste.setGenero(cliente.getGenero());
                    clienteExiste.setNombre(cliente.getNombre());
                    clienteExiste.setClave(cliente.getClave());
                    return clienteExiste;
                }
            )
            .map(clienteRepository :: save)
            .orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente con ci " + cliente.getIdentificacion() + "no existe")
            );
    }

    @Override
    public void deleteCliente(String identificacion) {
        clienteRepository.deleteByIdentificacion(identificacion);
    }

    @Override
    public List<CuentaMovimiento> getCuentaMovimientos(String identifiacion, LocalDate fechaInicial,
            LocalDate fechaFinal) {
        return clienteRepository.queryGetCuentaMovimientos(identifiacion, fechaInicial, fechaInicial);
    }

}
