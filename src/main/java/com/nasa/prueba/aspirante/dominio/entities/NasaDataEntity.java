package com.nasa.prueba.aspirante.dominio.entities;

import com.nasa.prueba.aspirante.dominio.dto.NasaDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nasa_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NasaDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String href;
    private String center;
    private String title;

    @Column(name = "nasa_id")
    private String nasa_id;

    @Column(name = "date_created")
    private LocalDateTime date_created;

    public NasaDataEntity(NasaDataDto nasaDataDto) {
        this.href = nasaDataDto.getCollection().getHref();
        List<NasaDataDto.NasaApiItem> items = nasaDataDto.getCollection().getItems();
        if (!items.isEmpty()) {
            NasaDataDto.NasaApiItem item = items.get(0);
            this.href = item.getHref();
            List<NasaDataDto.NasaApiData> data = item.getData();
            if (!data.isEmpty()) {
                NasaDataDto.NasaApiData firstData = data.get(0);
                this.center = firstData.getCenter();
                this.title = firstData.getTitle();
                this.nasa_id = firstData.getNasa_id();
                this.date_created = LocalDateTime.parse(firstData.getDate_created());
            }
        }
    }
}
