package com.nasa.prueba.aspirante.aplicacion.taskscheduler;

import com.nasa.prueba.aspirante.dominio.dto.NasaDataDto;
import com.nasa.prueba.aspirante.dominio.entities.NasaDataEntity;
import com.nasa.prueba.aspirante.infraestructura.clientrest.NasaApiClient;
import com.nasa.prueba.aspirante.infraestructura.repository.NasaDataRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@EnableScheduling
@Component
public class NasaApiTask {
    private final NasaApiClient nasaApiClient;
    private final NasaDataRepository nasaDataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(NasaApiTask.class);

    public NasaApiTask(NasaApiClient nasaApiClient, NasaDataRepository nasaDataRepository) {
        this.nasaApiClient = nasaApiClient;
        this.nasaDataRepository = nasaDataRepository;
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void execute() {
        LOGGER.info("Executing NASA API task");
        String searchTerm = "apollo 11";

        NasaDataDto nasaDataDto = nasaApiClient.searchNasaData(searchTerm);
        if (nasaDataDto != null && nasaDataDto.getCollection() != null && !nasaDataDto.getCollection().getItems().isEmpty()) {
            NasaDataEntity nasaDataEntity = convertDtoToEntity(nasaDataDto);
            nasaDataRepository.save(nasaDataEntity);
        }
    }

    private NasaDataEntity convertDtoToEntity(NasaDataDto nasaDataDto) {
        NasaDataDto.NasaApiItem firstItem = nasaDataDto.getCollection().getItems().get(0);
        NasaDataDto.NasaApiData firstData = firstItem.getData().get(0);

        NasaDataEntity nasaDataEntity = new NasaDataEntity();
        nasaDataEntity.setHref(firstItem.getHref());
        nasaDataEntity.setCenter(firstData.getCenter());
        nasaDataEntity.setTitle(firstData.getTitle());
        nasaDataEntity.setNasa_id(firstData.getNasa_id());

        OffsetDateTime offsetDateTime = OffsetDateTime.parse(firstData.getDate_created());
        LocalDateTime localDateTime = offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        nasaDataEntity.setDate_created(localDateTime);

        return nasaDataEntity;
    }
}

