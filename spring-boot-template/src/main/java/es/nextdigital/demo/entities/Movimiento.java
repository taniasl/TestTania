package es.nextdigital.demo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "MOVIMIENTO")
@NamedQueries({ 
	@NamedQuery(name = "Movimiento.findMovimientosByCuenta", query = "SELECT m FROM Movimiento WHERE m.cuenta.iban = ?")
	})
public class Movimiento implements Serializable{
	private static final  short INGRESO_EFECTIVO = 1;
	private static final short RETIRADA_EFECTIVO = 2;
	private static final  short COMISIONES = 3;
	private static final short TRANSFERENCIA_ENTRANTE = 4;
	private static final short TRANSFERENCIA_SALIENTE = 5;
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "TIPO")
	private Integer tipo;
	
	@Column(name = "IMPORTE")
	private Double importe;
	
	@JoinColumn(name = "cuenta", referencedColumnName = "iban")
	@OneToOne(optional = false)
	private Cuenta cuenta;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
