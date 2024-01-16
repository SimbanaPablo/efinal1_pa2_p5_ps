package com.uce.edu.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.repository.ICuentaBancariaRepository;
import com.uce.edu.repository.ITransferenciaRepository;
import com.uce.edu.repository.modelo.CuentaBancaria;
import com.uce.edu.repository.modelo.Transferencia;

@Service
public class TransferenciaServiceImpl implements ITransferenciaService {

	@Autowired
	private ITransferenciaRepository transferenciaRepository;

	@Autowired
	private ICuentaBancariaRepository cuentaBancariaRepository;

	@Override
	public Transferencia buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.transferenciaRepository.seleccionar(id);
	}

	@Override
	public void guardar(Transferencia transferencia) {
		// TODO Auto-generated method stub
		this.transferenciaRepository.insertar(transferencia);
	}

	@Override
	public void actualizar(Transferencia transferencia) {
		// TODO Auto-generated method stub
		this.transferenciaRepository.actualizar(transferencia);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.transferenciaRepository.eliminar(id);
	}

	@Override
	public void realizarTransferencia(String numeroOrigen, String numeroDestino, BigDecimal monto) {
		// TODO Auto-generated method stub
		Transferencia trans = new Transferencia();
		CuentaBancaria cuentaO = this.cuentaBancariaRepository.seleccionarPorNumero(numeroDestino);

		BigDecimal total = cuentaO.getSaldo().subtract(monto);

		CuentaBancaria cuentaD = this.cuentaBancariaRepository.seleccionarPorNumero(numeroDestino);

		cuentaD.setSaldo(total);

		this.cuentaBancariaRepository.actualizar(cuentaD);
		this.cuentaBancariaRepository.actualizar(cuentaO);
		
		trans.setCuentaDestino(cuentaD);
		trans.setCuentaOrigen(cuentaO);
		trans.setFecha(LocalDateTime.now());
		
		this.transferenciaRepository.insertar(trans);

	}

}
