package warehouse.services;

import warehouse.dao.PosterDao;
import warehouse.domains.Book;
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

    @Override
    public ResponseEntity<DataDto<List<Poster>>> findByAuthorName(String authorName) {
        try {
            List<Poster> refrigerators = posterDao.findAll().stream().filter(refrigerator ->
                    refrigerator.getAuthor_name().equals(authorName)).toList();
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
    public ResponseEntity<DataDto<Poster>> findBypageCount(Integer pageCount) {
        try {
            Poster refrigerator = posterDao.findAll().stream().filter(refrigerator1 ->
                    refrigerator1.getPageCount().equals(pageCount)).findFirst().orElse(null);
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

    public ResponseEntity<DataDto<List<Poster>>> findByTitle(String title) {
        try {
            List<Poster> refrigerators = posterDao.findAll().stream().filter(refrigerator ->
                    refrigerator.getTitle().equals(title)).toList();
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

    public ResponseEntity<DataDto<List<Poster>>> findByLength(Integer length) {
        try {
            List<Poster> refrigerators = posterDao.findAll().stream().filter(refrigerator ->
                    refrigerator.getLength().equals(length)).toList();
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

    public ResponseEntity<DataDto<Poster>> addPoster(String authorName, Double price, String title) {
        try {
            Poster poster = new Poster();
            poster.setId(random.nextLong(1000) + 1);
            poster.setTitle(title);
            poster.setAuthor_name(authorName);
            poster.setPrice(price);
            poster.setLength(random.nextInt(1000) + 1);
            poster.setPageCount(random.nextInt(1000) + 1);
            posterDao.saveData(poster);
            return new ResponseEntity<>(new DataDto<>(poster), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public String posterList() {
        try {
            List<Poster> posters = null;
            posters = posterDao.findAll();
            String posterList = "";
            int i = 0;
            if (posters.isEmpty()) {
                System.out.println(MessageHelper.POSTER_NOT_FOUND);
                return null;
            }
            for (Poster poster : posters) {
                String str = "";
                str = i + ")" + "| id: " + poster.getId() + "| authorName: " + poster.getAuthor_name() + " | title: " + poster.getTitle() + " |";
                System.out.println(str);
                posterList += str;
                i++;
            }
            if (posterList == "")
                System.out.println(MessageHelper.POSTER_NOT_FOUND);

            return posterList;
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<DataDto<Poster>> update(Long id, String newAuthorName, String newtitle) {
        try {
            ResponseEntity<DataDto<Poster>> byID = findByID(id);
            Poster poster = byID.getData().getBody();
            posterDao.removePoster(id);
            Poster poster1 = new Poster();
            poster1.setId(id);
            poster1.setPrice(poster.getPrice());
            poster1.setTitle(newtitle);
            poster1.setAuthor_name(newAuthorName);
            poster1.setLength(poster.getLength());
            poster1.setPageCount(poster.getPageCount());
            posterDao.saveData(poster1);
            return new ResponseEntity<>(new DataDto<>(poster1), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }
}
