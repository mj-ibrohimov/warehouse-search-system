import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import warehouse.domains.Poster;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.PosterService;

import java.util.List;

public class PosterServiceTest {
    private final PosterService service = new PosterService();

    @Test
    public void findByAllTest() {
        String sort = "1";
        ResponseEntity<DataDto<List<Poster>>> all = service.findAll(sort);
        Assertions.assertTrue(all.getData().isSuccess(), "Find all method is not passed!");
    }

    @Test
    public void findByIDTest() {
        Long id = 1L;
        ResponseEntity<DataDto<Poster>> responseEntity = service.findByID(id);
        Assertions.assertTrue(responseEntity.getData().isSuccess(), "Find by id method is not passed!");
    }

    @Test
    public void findByAuthorNameTest() {
        String authorName = "kimdir";
        ResponseEntity<DataDto<List<Poster>>> responseEntity = service.findByAuthorName(authorName);
        Assertions.assertEquals(responseEntity.getStatus(), 200, "Find by authorName method is not passed!");
    }

    @Test
    public void filterByPriceTest() {
        Double max = 25D;
        Double min = 15D;
        ResponseEntity<DataDto<List<Poster>>> all = service.filterByPrice(min, max);
        Assertions.assertTrue(all.getData().isSuccess(), "Filter by price method is not passed!!");
    }
    @Test
    public void findByTitle(){
        String title="nimadir";
        ResponseEntity<DataDto<List<Poster>>> responseEntity=service.findByTitle(title);
        Assertions.assertEquals(responseEntity.getStatus(),200,"Find By Title method is not passed!");
    }

}

