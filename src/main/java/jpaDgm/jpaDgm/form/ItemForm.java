package jpaDgm.jpaDgm.form;

import lombok.Data;

@Data
public class ItemForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String info;
    private String category;
    private String cateDetail;
}
