package warehouse.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Poster extends BaseDomain {
    private String title;
    private Integer length;
@Builder(builderMethodName = "childBuilder")
    public Poster(Long id, String author_name, Double price, Integer pageCount, String title, Integer length) {
        super(id, author_name, price, pageCount);
        this.title = title;
        this.length = length;
    }
}
