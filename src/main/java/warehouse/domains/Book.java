package warehouse.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseDomain {
    private String color;
    private Double width;
    private Double height;


    @Builder(builderMethodName = "childBuilder")
    public Book(Long id, String author_name, Double price, Integer pageCount, String color, Double width, Double height) {
        super(id, author_name, price, pageCount);
        this.color = color;
        this.width = width;
        this.height = height;
    }


}
