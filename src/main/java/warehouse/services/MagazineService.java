package warehouse.services;

import warehouse.dao.MagazineDao;
import warehouse.domains.Magazine;
import warehouse.domains.MessageHelper;
import warehouse.dtos.AppErrorDto;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.exceptions.GenericNotFoundException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MagazineService implements warehouse.services.BaseService<Magazine> {

    private final MagazineDao magazinedao = new MagazineDao();
    private final Random random = new Random();

    @Override
    public ResponseEntity<DataDto<List<Magazine>>> findAll(String sort) {
        try {
            List<Magazine> refrigerator = magazinedao.findAll();
            if (refrigerator.isEmpty()) {
                throw new GenericNotFoundException("Magazines not found!");
            }
            switch (sort) {
                case "1" -> refrigerator.sort(Comparator.comparing(Magazine::getId));
                case "2" -> refrigerator.sort(Comparator.comparing(Magazine::getPrice));
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
    public ResponseEntity<DataDto<Magazine>> findByID(Long id) {
        try {
            Magazine refrigerator = magazinedao.findAll().stream().filter(refrigerator1 ->
                    refrigerator1.getId().equals(id)).findFirst().orElse(null);
            if (refrigerator == null) {
                throw new GenericNotFoundException("Magazine not found!");
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
    public ResponseEntity<DataDto<List<Magazine>>> filterByPrice(Double min, Double max) {
        try {
            List<Magazine> refrigerators = magazinedao.findAll().stream().filter(refrigerator ->
                    refrigerator.getPrice() >= min && refrigerator.getPrice() <= max).toList();
            if (refrigerators.isEmpty()) {
                throw new GenericNotFoundException("Magazine not found!");
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
    public ResponseEntity<DataDto<List<Magazine>>> findByPrice(Double price) {
        try {
            List<Magazine> refrigerators = magazinedao.findAll().stream().filter(refrigerator ->
                    refrigerator.getPrice().equals(price)).toList();
            if (refrigerators.isEmpty()) {
                throw new GenericNotFoundException("Magazine not found!");
            }
            return new ResponseEntity<>(new DataDto<>(refrigerators), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

 