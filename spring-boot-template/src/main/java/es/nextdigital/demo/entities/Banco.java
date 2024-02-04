package es.nextdigital.demo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Banco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "ENTIDAD")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String entidad;
	
	@Column(name = "COMISIONES")
	private Double comisiones;

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Double getComisiones() {
		return comisiones;
	}

	public void setComisiones(Double comisiones) {
		this.comisiones = comisiones;
	}
	
	
	
}
