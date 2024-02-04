package dao;

import java.util.ArrayList;

import es.nextdigital.demo.entities.Movimiento;

public interface MovimientoDAO {

	public ArrayList<Movimiento>findByCuenta(int iban);
}
