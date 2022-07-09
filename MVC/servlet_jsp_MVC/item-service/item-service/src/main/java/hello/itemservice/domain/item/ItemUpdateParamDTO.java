package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemUpdateParamDTO {

    private String itemName;
    //price가 안들어가는 경우가 있을 경우를 대비
    //null이 들어갈 수 있는 경우 대비한것임
    private Integer price;
    private Integer quantity;

    public ItemUpdateParamDTO(){

    }

    public ItemUpdateParamDTO(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
