package com.nasa.prueba.aspirante.infraestructura.restcontroller;

import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;
import com.nasa.prueba.aspirante.infraestructura.repository.NasaDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nasa-data")
public class NasaDataController {
    private final NasaDataRepository nasaDataRepository;

    public NasaDataController(NasaDataRepository nasaDataRepository) {
        this.nasaDataRepository = nasaDataRepository;
    }

    @GetMapping
    public List<NasaDataEntity> getAllNasaData() {
        return nasaDataRepository.findAll();
    }

}
