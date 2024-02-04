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
@Table(name = "TARJETA")
@NamedQueries({ 
	@NamedQuery(name = "Tarjeta.findById", query = "SELECT a FROM Tarjeta a WHERE a.id = ?")
	})

public class Tarjeta  implements Serializable{
	
	public static final  short DEBITO = 1;
	public static final  short CREDITO = 2;
	
	
	@Id
    @Column(name = "NUMERO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer numero;
	
	@Column(name = "TIPO")
	private Integer tipo;
	
	@Column(name = "LIMITE")
	private Double limite;
	
	@Column(name = "ACTIVADA")
	private Boolean activada;
	
	@Column(name = "PIN")
	private String pin;
	
	@JoinColumn(name = "cuenta", referencedColumnName = "iban")
	@OneToOne(optional = false)
	private Cuenta cuenta;

	public Tarjeta() {
		
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public Boolean isActivada() {
		return activada;
	}

	public void setActivada(Boolean activada) {
		this.activada = activada;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
}
