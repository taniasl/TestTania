package dao;

import es.nextdigital.demo.entities.Tarjeta;

public interface TarjetaDAO {
	
	 public Tarjeta findById(int id);

	Tarjeta add(Tarjeta tarjeta) ;
}
