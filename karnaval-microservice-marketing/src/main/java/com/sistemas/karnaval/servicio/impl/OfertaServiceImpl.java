package com.sistemas.karnaval.servicio.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.karnaval.entidad.Oferta;
import com.sistemas.karnaval.repositorio.OfertaRepository;
import com.sistemas.karnaval.servicio.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public Oferta crear(Oferta entidad) {
        if (entidad.getFechaFin().isBefore(entidad.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        return ofertaRepository.save(entidad);
    }

    @Override
    public Oferta actualizar(Long id, Oferta entidad) {
        Oferta ofertaExistente = ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
        ofertaExistente.setNombre(entidad.getNombre());
        ofertaExistente.setDescripcion(entidad.getDescripcion());
        ofertaExistente.setPorcentaje(entidad.getPorcentaje());
        ofertaExistente.setFechaInicio(entidad.getFechaInicio());
        ofertaExistente.setFechaFin(entidad.getFechaFin());
        ofertaExistente.setEstado(entidad.getEstado());
        return ofertaRepository.save(ofertaExistente);
    }

    @Override
    public void eliminar(Long id) {
        ofertaRepository.deleteById(id);
    }

    @Override
    public Oferta buscarPorId(Long id) {
        return ofertaRepository.findById(id).orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
    }

    @Override
    public List<Oferta> listarTodos() {
        return ofertaRepository.findAll();
    }

    @Override
    public List<Oferta> listarActivas() {
        return ofertaRepository.findAll().stream()
                .filter(oferta -> oferta.getEstado() && !LocalDate.now().isAfter(oferta.getFechaFin()))
                .toList();
    }
}
