package warehouse.controller;

import warehouse.dao.BookDao;
import warehouse.dao.PosterDao;
import warehouse.domains.Poster;
import warehouse.domains.MessageHelper;
import warehouse.domains.Poster;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.PosterService;
import warehouse.utils.BaseUtils;

import java.util.InputMismatchException;
import java.util.List;

public class PosterController implements BaseController{

private final PosterService posterService=new PosterService();
private final PosterDao posterDao=new PosterDao();
    @Override
    public void showAll(String sort) {
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.findAll(sort);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findById() {
        BaseUtils.print("Enter id: ");
        ResponseEntity<DataDto<Poster>> responseEntity = posterService.findByID(BaseUtils.readLong());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByAuthorName() {
        BaseUtils.print("Enter color: ");
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.findByAuthorName(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPrice() {
        BaseUtils.print("Enter price: ");
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.findByPrice(BaseUtils.readDouble());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void filterByPrice() {
        BaseUtils.print("Enter min: ");
        Double min = BaseUtils.readDouble();
        BaseUtils.print("Enter max: ");
        Double max = BaseUtils.readDouble();
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.filterByPrice(min, max);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPageCount() {
        BaseUtils.print("Enter pageCount: ");
        ResponseEntity<DataDto<Poster>> responseEntity = posterService.findBypageCount(BaseUtils.readInteger());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }
    public void findByTitle() {
        BaseUtils.print("Enter title: ");
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.findByTitle(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }
    public void findByLength() {
        BaseUtils.print("Enter length: ");
        ResponseEntity<DataDto<List<Poster>>> responseEntity = posterService.findByLength(BaseUtils.readInteger());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }


    public void addPoster() {
        try {
            BaseUtils.print("Enter Poster authorName: ");
            String authorName = BaseUtils.readText();
            BaseUtils.print("Enter price: ");
            Double price = BaseUtils.readDouble();
            BaseUtils.print("Enter title: ");
            String title = BaseUtils.readText();
            ResponseEntity<DataDto<Poster>> responseEntity = posterService.addPoster(authorName, price, title);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void updatePoster() {
        try {
            posterService.posterList();
            BaseUtils.print("Select Poster id: ");
            Long id = BaseUtils.readLong();
            BaseUtils.print("Enter new authorName: ");
            String newAuthorName = BaseUtils.readText();
            BaseUtils.print("Enter new title: ");
            String newtitle = BaseUtils.readText();
            ResponseEntity<DataDto<Poster>> responseEntity = posterService.update(id, newAuthorName, newtitle);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void deletePoster() {
        try {
            posterService.posterList();
            System.out.print("Select Poster(id): ");
            Long posterId = BaseUtils.readLong();
            posterDao.removePoster(posterId);
            System.out.println(MessageHelper.POSTER_DELETED);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }
}
