package prueba.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import prueba.banco.entity.Cuenta;
import java.util.List;


public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findByIdCliente(Long idCliente);
    @Query(value = "select numero_cuenta from tbl_cuenta where id_cuenta = :idCuenta", nativeQuery = true)
    String queryByIdCuenta(@Param("idCuenta")Integer idCuenta);
}
