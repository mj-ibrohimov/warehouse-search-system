package warehouse.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Magazine extends BaseDomain {
    private String front_of_book_content;
    private String type;

    @Builder(builderMethodName = "childBuilder")
    public Magazine(Long id, String author_name, Double price, Integer pageCount, String front_of_book_content, String type) {
        super(id, author_name, price, pageCount);
        this.front_of_book_content = front_of_book_content;
        this.type = type;
    }
}
