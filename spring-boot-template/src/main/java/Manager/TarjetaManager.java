package Manager;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.TarjetaDAO;
import es.nextdigital.demo.entities.Tarjeta;

public class TarjetaManager extends HibernateDaoSupport implements TarjetaDAO{

	@Override
	public Tarjeta findById(int id) {
		return findTarjeta("Tarjeta.findById",id).get(0);
	}
	
	@Override
	public Tarjeta add(Tarjeta tarjeta)  {
		
		try{
	        this.getHibernateTemplate().saveOrUpdate(tarjeta);
	        this.getHibernateTemplate().flush();
		} catch (DataAccessException e){
			e.printStackTrace();
			
		}
		return tarjeta;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Tarjeta> findTarjeta(String namedQuery, int id) {
		return (ArrayList<Tarjeta>) this.getHibernateTemplate().findByNamedQuery(namedQuery, id);
	}
}
