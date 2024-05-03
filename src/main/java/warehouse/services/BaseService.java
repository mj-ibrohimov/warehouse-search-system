package warehouse.services;

import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

public interface BaseService<T> {
    ResponseEntity<DataDto<List<T>>> findAll(String sort);

    ResponseEntity<DataDto<T>> findByID(Long id);

    ResponseEntity<DataDto<List<T>>> filterByPrice(Double min, Double max);

    ResponseEntity<DataDto<List<T>>> findByPrice(Double price);

    ResponseEntity<DataDto<List<T>>> findByAuthorName(String authorName);

    ResponseEntity<DataDto<T>> findBypageCount(Integer pageCount);
}
