package warehouse.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDomain {
    protected Long id;
    protected String author_name;
    protected Double price;
    protected Integer pageCount;


}
