package com.sistemas.karnaval.servicio;

import java.util.List;

import com.sistemas.karnaval.entidad.Oferta;

public interface OfertaService extends iGenericoService<Oferta, Long> {

	List<Oferta> listarActivas();
}
