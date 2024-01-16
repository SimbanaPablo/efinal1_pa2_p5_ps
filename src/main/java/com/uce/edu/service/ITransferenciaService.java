package com.uce.edu.service;

import java.math.BigDecimal;

import com.uce.edu.repository.modelo.Transferencia;

public interface ITransferenciaService {
	public Transferencia buscar(Integer id);

	public void guardar(Transferencia transferencia);

	public void actualizar(Transferencia transferencia);

	public void eliminar(Integer id);

	public void realizarTransferencia(String numeroOrigen, String numeroDestino, BigDecimal monto);
}
