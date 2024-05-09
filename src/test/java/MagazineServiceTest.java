import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import warehouse.domains.Magazine;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.services.MagazineService;

import java.util.List;

public class MagazineServiceTest {

    private final MagazineService service = new MagazineService();

    @Test
    public void findByAllTest() {
        String sort = "1";
        ResponseEntity<DataDto<List<Magazine>>> all = service.findAll(sort);
        Assertions.assertTrue(all.getData().isSuccess(), "Find all method is not passed!");
    }

    @Test
    public void findByIDTest() {
        Long id = 1L;
        ResponseEntity<DataDto<Magazine>> responseEntity = service.findByID(id);
        Assertions.assertTrue(responseEntity.getData().isSuccess(), "Find by id method is not passed!");
    }

    @Test
    public void findByAuthorNameTest() {
        String authorName = "kimdir";
        ResponseEntity<DataDto<List<Magazine>>> responseEntity = service.findByAuthorName(authorName);
        Assertions.assertEquals(responseEntity.getStatus(), 200, "Find by authorName method is not passed!");
    }

    @Test
    public void filterByPriceTest() {
        Double max = 25D;
        Double min = 15D;
        ResponseEntity<DataDto<List<Magazine>>> all = service.filterByPrice(min, max);
        Assertions.assertTrue(all.getData().isSuccess(), "Filter by price method is not passed!!");
    }


}
