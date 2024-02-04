package es.nextdigital.demo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Cuenta implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "IBAN")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer iban;
	
	@Column(name = "SALDO")
	private Double saldo;
	
	
	@JoinColumn(name = "banco_entidad", referencedColumnName = "entidad")
	@ManyToOne(optional = false)
	private Banco banco;

	public Integer getIban() {
		return iban;
	}

	public void setIban(Integer iban) {
		this.iban = iban;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
}
