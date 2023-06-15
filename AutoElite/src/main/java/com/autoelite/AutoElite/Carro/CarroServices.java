package com.autoelite.AutoElite.Carro;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroServices {
    private final CarroDAO carroDAO;

    public CarroServices(CarroDAO carroDAO){
        this.carroDAO = carroDAO;
    }

    public List<Carro> getAllCarro() {
        return carroDAO.findAll();
    }

    public Carro getCarroById(String id) {
        return carroDAO.findById(id).orElseThrow(() -> new RuntimeException("Carro not found with id: " + id));
    }

    public void addCarro(Carro Carro) {
        carroDAO.save(Carro);
    }

    public void deleteCarro(String id) {
        carroDAO.deleteById(id);
    }

    public void updateCarro(String id, Carro carro) {
        Optional<Carro> existingCarro = carroDAO.findById(id);
        if (existingCarro.isPresent()) {
            if (carro.getCiudad() != null && !carro.getCiudad().isEmpty()) {
                existingCarro.get().setCiudad(carro.getCiudad());
            }
            if (carro.getPuertas() != null && !carro.getPuertas().isEmpty()) {
                existingCarro.get().setPuertas(carro.getPuertas());
            }
            if (carro.getMotor() != null && !carro.getMotor().isEmpty()) {
                existingCarro.get().setMotor(carro.getMotor());
            }
            if (carro.getCiudadMatricula() != null && !carro.getCiudadMatricula().isEmpty()) {
                existingCarro.get().setCiudadMatricula(carro.getCiudadMatricula());
            }
            if (carro.getMarca() != null && !carro.getMarca().isEmpty()) {
                existingCarro.get().setMarca(carro.getMarca());
            }
            if (carro.getPlaca() != null && !carro.getPlaca().isEmpty()) {
                existingCarro.get().setPlaca(carro.getPlaca());
            }
            if (carro.getColor() != null && !carro.getColor().isEmpty()) {
                existingCarro.get().setColor(carro.getColor());
            }
            if (carro.getTipo() != null && !carro.getTipo().isEmpty()) {
                existingCarro.get().setTipo(carro.getTipo());
            }
            if (carro.getCombustible() != null && !carro.getCombustible().isEmpty()) {
                existingCarro.get().setCombustible(carro.getCombustible());
            }
            if (carro.getYear() != null && !carro.getYear().isEmpty()) {
                existingCarro.get().setYear(carro.getYear());
            }
            if (carro.getEstado() != null && !carro.getEstado().isEmpty()) {
                existingCarro.get().setEstado(carro.getEstado());
            }
            carroDAO.save(existingCarro.get());
        } else {
            throw new RuntimeException("Carro no encontrado: " + id);
        }
    }

}
