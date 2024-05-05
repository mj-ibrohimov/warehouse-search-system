package warehouse.services;

import warehouse.dao.PosterDao;
import warehouse.domains.MessageHelper;
import warehouse.domains.Poster;
import warehouse.dtos.AppErrorDto;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.exceptions.GenericNotFoundException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PosterService implements BaseService<Poster> {

    private final PosterDao posterDao = new PosterDao();
    private final Random random = new Random();

    @Override
    public ResponseEntity<DataDto<List<Poster>>> findAll(String sort) {
        try {
            List<Poster> refrigerator = posterDao.findAll();
            if (refrigerator.isEmpty()) {
                throw new GenericNotFoundException("Posters not found!");
            }
            switch (sort) {
                case "1" -> refrigerator.sort(Comparator.comparing(Poster::getId));
                case "2" -> refrigerator.sort(Comparator.comparing(Poster::getPrice));
            }
            return new ResponseEntity<>(new DataDto<>(refrigerator));
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<Poster>> findByID(Long id) {
        try {
            Poster refrigerator = posterDao.findAll().stream().filter(refrigerator1 ->
                    refrigerator1.getId().equals(id)).findFirst().orElse(null);
            if (refrigerator == null) {
                throw new GenericNotFoundException("Posters not found!");
            }
            return new ResponseEntity<>(new DataDto<>(refrigerator), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<Poster>>> filterByPrice(Double min, Double max) {
        try {
            List<Poster> refrigerators = posterDao.findAll().stream().filter(refrigerator ->
                    refrigerator.getPrice() >= min && refrigerator.getPrice() <= max).toList();
            if (refrigerators.isEmpty()) {
                throw new GenericNotFoundException("Posters not found!");
            }
            return new ResponseEntity<>(new DataDto<>(refrigerators), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<Poster>>> findByPrice(Double price) {
        try {
            List<Poster> refrigerators = posterDao.findAll().stream().filter(refrigerator ->
                    refrigerator.getPrice().equals(price)).toList();
            if (refrigerators.isEmpty()) {
                throw new GenericNotFoundException("Posters not found!");
            }
            return new ResponseEntity<>(new DataDto<>(refrigerators), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }
}
