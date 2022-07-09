package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data//이거 좀 위험함 @Getter @Setter정도는 써도 좋음
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    //price가 안들어가는 경우가 있을 경우를 대비
    //null이 들어갈 수 있는 경우 대비한것임
    private Integer price;
    private Integer quantity;

    public Item(){

    }

    //id 제외 생성자 만들기
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
