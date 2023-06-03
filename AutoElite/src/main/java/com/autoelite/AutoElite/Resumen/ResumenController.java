package com.autoelite.AutoElite.Resumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/resumen")
public class ResumenController {

    private final ResumenService resumenService;

    @Autowired
    public ResumenController(ResumenService resumenService) {
        this.resumenService = resumenService;
    }

    @GetMapping("/")
    public List<Resumen> getAllSummaries() {
        return resumenService.getAllSummaries();
    }
}
