import warehouse.domains.Book;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BookServiceTest {
    private final BookService service = new BookService();

    @Test
    public void findByAllTest() {
        String sort = "1";
        ResponseEntity<DataDto<List<Book>>> all = service.findAll(sort);
        Assertions.assertTrue(all.getData().isSuccess(), "Find all method is not passed!");
    }

    @Test
    public void findByIDTest() {
        Long id = 1L;
        ResponseEntity<DataDto<Book>> responseEntity = service.findByID(id);
        Assertions.assertTrue(responseEntity.getData().isSuccess(), "Find by id method is not passed!");
    }

    @Test
    public void findByColorTest() {
        String color = "white";
        ResponseEntity<DataDto<List<Book>>> responseEntity = service.findByColor(color);
        Assertions.assertEquals(responseEntity.getStatus(), 200, "Find by color method is not passed!");
    }

    @Test
    public void filterByPriceTest() {
        Double max = 25D;
        Double min = 15D;
        ResponseEntity<DataDto<List<Book>>> all = service.filterByPrice(min, max);
        Assertions.assertTrue(all.getData().isSuccess(), "Filter by price method is not passed!!");
    }

    @Test
    public void findByWidthAndHeightTest() {
        Double width = 22D;
        Double height = 11D;
        ResponseEntity<DataDto<List<Book>>> all = service.findByWidthAndHeight(width, height);
        Assertions.assertTrue(all.getData().isSuccess(), "Find by width and height method is not passed!!");
    }
}
