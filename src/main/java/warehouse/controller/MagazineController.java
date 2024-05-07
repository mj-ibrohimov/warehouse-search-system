package warehouse.controller;

import warehouse.dao.MagazineDao;
import warehouse.domains.Magazine;
import warehouse.domains.MessageHelper;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.MagazineService;
import warehouse.utils.BaseUtils;

import java.util.InputMismatchException;
import java.util.List;

public class MagazineController implements BaseController{

    private final MagazineService magazineService=new MagazineService();
    private final MagazineDao magazineDao=new MagazineDao();

    @Override
    public void showAll(String sort) {
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = magazineService.findAll(sort);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findById() {
        BaseUtils.print("Enter id: ");
        ResponseEntity<DataDto<Magazine>> responseEntity = magazineService.findByID(BaseUtils.readLong());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByAuthorName() {
        BaseUtils.print("Enter AuthorName: ");
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = magazineService.findByAuthorName(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPrice() {
        BaseUtils.print("Enter price: ");
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = magazineService.findByPrice(BaseUtils.readDouble());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void filterByPrice() {
        BaseUtils.print("Enter min: ");
        Double min = BaseUtils.readDouble();
        BaseUtils.print("Enter max: ");
        Double max = BaseUtils.readDouble();
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = magazineService.filterByPrice(min, max);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPageCount() {
        BaseUtils.print("Enter pageCount: ");
        ResponseEntity<DataDto<Magazine>> responseEntity = magazineService.findBypageCount(BaseUtils.readInteger());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void findfront_of_book_content(){
        BaseUtils.print("Enter findfront_of_book_content: ");
        ResponseEntity<DataDto<Magazine>> responseEntity = magazineService.findByfront_of_book_content(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void findByType(){
        BaseUtils.print("Enter type: ");
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = magazineService.findByType(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void addMagazine() {
        try {
            BaseUtils.print("Enter Magazine authorName: ");
            String authorName = BaseUtils.readText();
            BaseUtils.print("Enter price: ");
            Double price = BaseUtils.readDouble();
            BaseUtils.print("Enter front_of_book_content: ");
            String front_of_book_content = BaseUtils.readText();
            ResponseEntity<DataDto<Magazine>> responseEntity = magazineService.addMagazine(authorName, price, front_of_book_content);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void updateMagazine() {
        try {
            magazineService.magazineList();
            BaseUtils.print("Select Magazine id: ");
            Long id = BaseUtils.readLong();
            BaseUtils.print("Enter new authorName: ");
            String newAuthorName = BaseUtils.readText();
            BaseUtils.print("Enter new front_of_book_content: ");
            String front_of_book_content = BaseUtils.readText();
            ResponseEntity<DataDto<Magazine>> responseEntity = magazineService.update(id, newAuthorName, front_of_book_content);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void deleteMagazine() {
        try {
            magazineService.magazineList();
            System.out.print("Select User(id): ");
            Long bookId = BaseUtils.readLong();
            magazineDao.removeMagazine(bookId);
            System.out.println(MessageHelper.MAGAZINE_DELETED);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }
}
