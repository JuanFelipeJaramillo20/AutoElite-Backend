package com.autoelite.AutoElite.carro;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carro")
public class CarroController {
    private final CarroServices carroServices;

    public CarroController(CarroServices carroServices)
    {
        this.carroServices = carroServices;
    }

    @GetMapping
    public List<Carro> getCarro() {
        return carroServices.getAllCarro();
    }

    @GetMapping("{idCarro}")
    public Carro getCarroById(@PathVariable("idCarro") String id) {
        return carroServices.getCarroById(id);
    }

    @PostMapping
    public void addCarro(@RequestBody Carro carro) {
        carroServices.addCarro(carro);
    }

    @DeleteMapping("{idCarro}")
    public void deleteCarro(@PathVariable("idCarro") String id) {
        carroServices.deleteCarro(id);
    }

    @PutMapping("{idCarro}")
    public void updateCarro(@PathVariable("idCarro") String id, @RequestBody Carro carro) {
        carroServices.updateCarro(id, carro);
    }
}
