package warehouse.services;

import warehouse.dao.BookDao;
import warehouse.domains.Book;
import warehouse.domains.MessageHelper;
import warehouse.domains.User;
import warehouse.domains.UserRole;
import warehouse.dtos.AppErrorDto;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.exceptions.GenericNotFoundException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BookService implements BaseService<Book> {

    private final BookDao bookDao = new BookDao();
    private final Random random = new Random();

    @Override
    public ResponseEntity<DataDto<List<Book>>> findAll(String sort) {
        try {
            List<Book> book = bookDao.findAll();
            if (book.isEmpty()) {
                throw new GenericNotFoundException("Books not found!");
            }
            switch (sort) {
                case "1" -> book.sort(Comparator.comparing(Book::getId));
                case "2" -> book.sort(Comparator.comparing(Book::getPrice));
            }
            return new ResponseEntity<>(new DataDto<>(book));
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<Book>> findByID(Long id) {
        try {
            Book book = bookDao.findAll().stream().filter(book1 ->
                    book1.getId().equals(id)).findFirst().orElse(null);
            if (book == null) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(book), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<Book>>> filterByPrice(Double min, Double max) {
        try {
            List<Book> books = bookDao.findAll().stream().filter(book ->
                    book.getPrice() >= min && book.getPrice() <= max).toList();
            if (books.isEmpty()) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(books), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<Book>>> findByPrice(Double price) {
        try {
            List<Book> books = bookDao.findAll().stream().filter(book ->
                    book.getPrice().equals(price)).toList();
            if (books.isEmpty()) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(books), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<Book>>> findByAuthorName(String authorName) {
        try {
            List<Book> books = bookDao.findAll().stream().filter(book ->
                    book.getAuthor_name().equals(authorName)).toList();
            if (books.isEmpty()) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(books), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public ResponseEntity<DataDto<Book>> findBypageCount(Integer pageCount) {
        try {
            Book book = bookDao.findAll().stream().filter(book1 ->
                    book1.getPageCount().equals(pageCount)).findFirst().orElse(null);
            if (book == null) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(book), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public ResponseEntity<DataDto<List<Book>>> findByColor(String color) {
        try {
            List<Book> books = bookDao.findAll().stream().filter(book ->
                    book.getColor().equals(color)).toList();
            if (books.isEmpty()) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(books), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }


    public ResponseEntity<DataDto<List<Book>>> findByWidthAndHeight(Double width, Double height) {
        try {
            List<Book> keyboards = bookDao.findAll().stream().filter(keyboard ->
                    keyboard.getWidth().equals(width) && keyboard.getHeight().equals(height)).toList();
            if (keyboards.isEmpty()) {
                throw new GenericNotFoundException("Book not found!");
            }
            return new ResponseEntity<>(new DataDto<>(keyboards), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public ResponseEntity<DataDto<Book>> addBook(String authorName, Double price, String color) {
        try {
            Book book = new Book();
            book.setId(random.nextLong(1000) + 1);
            book.setColor(color);
            book.setAuthor_name(authorName);
            book.setPrice(price);
            book.setWidth(random.nextDouble(1000) + 1);
            book.setHeight(random.nextDouble(1000) + 1);
            book.setPageCount(random.nextInt(1000) + 1);
            bookDao.saveData(book);
            return new ResponseEntity<>(new DataDto<>(book), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public String bookList() {
        try {
            List<Book> books = null;
            books = bookDao.findAll();
            String bookList = "";
            int i = 0;
            if (books.isEmpty()) {
                System.out.println("Book not found!");
                return null;
            }
            for (Book book : books) {
                String str = "";
                str = i + ")" + "| id: " + book.getId() + "| authorName: " + book.getAuthor_name() + " | color: " + book.getColor() + " |";
                System.out.println(str);
                bookList += str;
                i++;
            }
            if (bookList == "")
                System.out.println(MessageHelper.BOOK_NOT_FOUND);

            return bookList;
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<DataDto<Book>> update(Long id, String newAuthorName, String newColor) {
        try {
            ResponseEntity<DataDto<Book>> byID = findByID(id);
            Book book = byID.getData().getBody();
            bookDao.removeBook(id);
            Book book1 = new Book();
            book1.setId(id);
            book1.setPrice(book.getPrice());
            book1.setColor(newColor);
            book1.setAuthor_name(newAuthorName);
            book1.setHeight(book.getHeight());
            book1.setWidth(book.getWidth());
            book1.setPageCount(book.getPageCount());
            bookDao.saveData(book1);
            return new ResponseEntity<>(new DataDto<>(book1), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(e.getMessage())
                    .developerMessage(e.getMessage())
                    .build()), 400);
        }
    }
}
