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

public class MagazineService implements BaseService<Magazine> {

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

    @Override
    public ResponseEntity<DataDto<List<Magazine>>> findByAuthorName(String authorName) {
        try {
            List<Magazine> refrigerators = magazinedao.findAll().stream().filter(refrigerator ->
                    refrigerator.getAuthor_name().equals(authorName)).toList();
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
    public ResponseEntity<DataDto<Magazine>> findBypageCount(Integer pageCount) {
        try {
            Magazine refrigerator = magazinedao.findAll().stream().filter(refrigerator1 ->
                    refrigerator1.getPageCount().equals(pageCount)).findFirst().orElse(null);
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

    public ResponseEntity<DataDto<Magazine>> findByfront_of_book_content(String fron_content) {
        try {
            Magazine refrigerator = magazinedao.findAll().stream().filter(refrigerator1 ->
                    refrigerator1.getFront_of_book_content().equals(fron_content)).findFirst().orElse(null);
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

    public ResponseEntity<DataDto<List<Magazine>>> findByType(String type) {
        try {
            List<Magazine> refrigerators = magazinedao.findAll().stream().filter(refrigerator ->
                    refrigerator.getType().equals(type)).toList();
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

    public ResponseEntity<DataDto<Magazine>> addMagazine(String authorName, Double price, String frontOfBookContent) {
        try {
            Magazine magazine = new Magazine();
            magazine.setId(random.nextLong(1000) + 1);
            magazine.setType(null);
            magazine.setAuthor_name(authorName);
            magazine.setPrice(price);
            magazine.setFront_of_book_content(frontOfBookContent);
            magazine.setPageCount(random.nextInt(1000) + 1);
            magazinedao.saveData(magazine);
            return new ResponseEntity<>(new DataDto<>(magazine), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public String magazineList() {
        try {
            List<Magazine> magazines = null;
            magazines = magazinedao.findAll();
            String magazineList = "";
            int i = 0;
            if (magazines.isEmpty()) {
                System.out.println(MessageHelper.MAGAZINE_NOT_FOUND);
                return null;
            }
            for (Magazine magazine : magazines) {
                String str = "";
                str = i + ")" + "| id: " + magazine.getId() + "| authorName: " + magazine.getAuthor_name() + " | front_of_book_content: " + magazine.getFront_of_book_content() + " |";
                System.out.println(str);
                magazineList += str;
                i++;
            }
            if (magazineList == "")
                System.out.println(MessageHelper.MAGAZINE_NOT_FOUND);

            return magazineList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<DataDto<Magazine>> update(Long id, String newAuthorName, String frontOfMagazineContent) {
        try {
            ResponseEntity<DataDto<Magazine>> byID = findByID(id);
            Magazine magazine = byID.getData().getBody();
            magazinedao.removeMagazine(id);
            Magazine magazine1 = new Magazine();
            magazine1.setId(id);
            magazine1.setPrice(magazine.getPrice());
            magazine1.setType(magazine.getType());
            magazine1.setAuthor_name(newAuthorName);
            magazine1.setFront_of_book_content(frontOfMagazineContent);
            magazine1.setPageCount(magazine.getPageCount());
            magazinedao.saveData(magazine1);
            return new ResponseEntity<>(new DataDto<>(magazine1), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }
}
