package warehouse.controller;

import warehouse.dao.BookDao;
import warehouse.domains.Book;
import warehouse.domains.MessageHelper;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.BookService;
import warehouse.utils.BaseUtils;

import java.util.InputMismatchException;
import java.util.List;

public class BookController implements BaseController {

    private final BookService bookService = new BookService();
    private final BookDao bookDao = new BookDao();

    @Override
    public void showAll(String sort) {
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.findAll(sort);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findById() {
        BaseUtils.print("Enter id: ");
        ResponseEntity<DataDto<Book>> responseEntity = bookService.findByID(BaseUtils.readLong());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByAuthorName() {
        BaseUtils.print("Enter authName: ");
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.findByAuthorName(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPrice() {
        BaseUtils.print("Enter price: ");
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.findByPrice(BaseUtils.readDouble());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void filterByPrice() {
        BaseUtils.print("Enter min: ");
        Double min = BaseUtils.readDouble();
        BaseUtils.print("Enter max: ");
        Double max = BaseUtils.readDouble();
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.filterByPrice(min, max);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    @Override
    public void findByPageCount() {
        BaseUtils.print("Enter pageCount: ");
        ResponseEntity<DataDto<Book>> responseEntity = bookService.findBypageCount(BaseUtils.readInteger());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void findByColor() {
        BaseUtils.print("Enter color: ");
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.findByColor(BaseUtils.readText());
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void findByWidthAndHeight() {
        BaseUtils.print("Enter width: ");
        Double width = BaseUtils.readDouble();
        BaseUtils.print("Enter height: ");
        Double height = BaseUtils.readDouble();
        ResponseEntity<DataDto<List<Book>>> responseEntity = bookService.findByWidthAndHeight(width, height);
        BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
    }

    public void addBook() {
        try {
            BaseUtils.print("Enter Book authorName: ");
            String authorName = BaseUtils.readText();
            BaseUtils.print("Enter Book price: ");
            Double price = BaseUtils.readDouble();
            BaseUtils.print("Enter color: ");
            String color = BaseUtils.readText();
            ResponseEntity<DataDto<Book>> responseEntity = bookService.addBook(authorName, price, color);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void updateBook() {
        try {
            bookService.bookList();
            BaseUtils.print("Select Book id: ");
            Long id = BaseUtils.readLong();
            BaseUtils.print("Enter new authorName: ");
            String newAuthorName = BaseUtils.readText();
            BaseUtils.print("Enter newcolor: ");
            String newColor = BaseUtils.readText();
            ResponseEntity<DataDto<Book>> responseEntity = bookService.update(id, newAuthorName, newColor);
            BaseUtils.print(BaseUtils.gson.toJson(responseEntity));
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void deleteBook() {
        try {
            bookService.bookList();
            System.out.print("Select Book(id): ");
            Long bookId = BaseUtils.readLong();
            bookDao.removeBook(bookId);
            System.out.println(MessageHelper.BOOK_DELETED);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }
}
