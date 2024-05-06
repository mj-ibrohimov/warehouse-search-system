package warehouse.controller;

public interface BaseController {

    void showAll(String sort);

    void findById();

    void findByAuthorName();

    void findByPrice();

    void filterByPrice();

    void findByPageCount();

}
