package Manager;

import java.util.ArrayList;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.MovimientoDAO;
import es.nextdigital.demo.entities.Movimiento;
import es.nextdigital.demo.entities.Tarjeta;


public class MovimientoManager extends HibernateDaoSupport implements MovimientoDAO{

	@Override
	public ArrayList<Movimiento> findByCuenta(int iban) {
		// TODO Auto-generated method stub
		return findMovimientoByCuenta("Movimiento.findMovimientosByCuenta",iban);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Movimiento> findMovimientoByCuenta(String namedQuery, int iban) {
		return (ArrayList<Movimiento>) this.getHibernateTemplate().findByNamedQuery(namedQuery, iban);
	}
}
