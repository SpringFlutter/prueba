package prueba.banco.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CuentaMovimiento {
    LocalDate getFechaMovimiento();
	LocalTime getHoraMovimiento(); 
	String getNombre(); 
	String getIdentificacion();
	String getNumeroCuenta();
	String getTipoCuenta();
    Double getSaldoInicial();
	Boolean getEstado();
	String getTipoMovimiento(); 
    String getMovimiento();
	Double getSaldoDisponible();
}
