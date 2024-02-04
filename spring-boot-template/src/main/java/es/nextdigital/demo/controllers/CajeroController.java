package es.nextdigital.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Manager.MovimientoManager;
import Manager.TarjetaManager;
import es.nextdigital.demo.entities.Cuenta;
import es.nextdigital.demo.entities.Movimiento;
import es.nextdigital.demo.entities.Tarjeta;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CajeroController {

	private static final String claveSecreta = "clave_secreta";

	private TarjetaManager tarjetaManager;
	private MovimientoManager movimientoManager;
	private Cuenta cuenta;
	private Movimiento movimiento;

	@RequestMapping(method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String accion = request.getParameter("accion");

			int numeroTarjeta = Integer.valueOf(request.getParameter("numeroTarjeta"));

			Tarjeta tarjeta = tarjetaManager.findById(numeroTarjeta);
			switch (accion) {
			case "ConsultarMovimientos":
				getMovimientos(tarjeta, response);
				break;
			case "SacarDinero":
				sacarDinero(tarjeta, request);
				break;
			case "IngresarDinero":
				break;
			case "HacerTranferencia":
				break;
			case "ActivarTarjeta":
				break;
			case "CambiarPIN":
				cambiarPIN(tarjeta,request);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getMovimientos(Tarjeta tarjeta, HttpServletResponse response) {

		movimientoManager.findByCuenta(tarjeta.getCuenta().getIban());
	}

	private boolean sacarDinero(Tarjeta tarjeta, HttpServletRequest request) throws Exception {
		int importe = Integer.valueOf(request.getParameter("importe"));
		String entidad = request.getParameter("entidad");
		Cuenta cuenta = tarjeta.getCuenta();
		Double importeComisiones;
		Double saldo = cuenta.getSaldo();
		if (tarjeta.isActivada()) {
			importeComisiones = (cuenta.getBanco().getEntidad() != entidad) 
                    ? importe * cuenta.getBanco().getComisiones()
                    : (double) importe;
			
			
			cuenta.setSaldo(saldo-importeComisiones);
			if ((Tarjeta.CREDITO == tarjeta.getTipo() && importeComisiones < tarjeta.getLimite())
				    || (Tarjeta.DEBITO == tarjeta.getTipo() && importeComisiones < cuenta.getSaldo())) {
				    return true;
				}

				return false;

		} else {
			tarjeta.setActivada(true);
			cambiarPIN(tarjeta, request);
		}
		return false;

	}

	private void cambiarPIN(Tarjeta tarjeta, HttpServletRequest request) throws Exception {
		String nuevoPin = request.getParameter("pin");
		String pinEncriptado;
		if (nuevoPin.length() < 4) {
			tarjeta.setPin(nuevoPin);
			pinEncriptado = encriptarPin(nuevoPin);
			tarjeta.setPin(pinEncriptado);
			tarjetaManager.add(tarjeta);
		}

	}

	public static String encriptarPin(String pin) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(claveSecreta.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] pinEncriptado = cipher.doFinal(pin.getBytes());
		return Base64.getEncoder().encodeToString(pinEncriptado);
	}

	public static String desencriptarPin(String pinEncriptado) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(claveSecreta.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] pinDesencriptado = cipher.doFinal(Base64.getDecoder().decode(pinEncriptado));
		return new String(pinDesencriptado);
	}

	public TarjetaManager getTarjetaManager() {
		return tarjetaManager;
	}

	public void setTarjetaManager(TarjetaManager tarjetaManager) {
		this.tarjetaManager = tarjetaManager;
	}

	public MovimientoManager getMovimientoManager() {
		return movimientoManager;
	}

	public void setMovimientoManager(MovimientoManager movimientoManager) {
		this.movimientoManager = movimientoManager;
	}

}
