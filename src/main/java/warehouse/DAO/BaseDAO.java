package warehouse.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BaseDao<T> {
    List<T> findAll() throws IOException;
}
