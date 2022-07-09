package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//컴포넌트 스캔의 대상임
//만약 데이터베이스에 연결되는 것이라면
// 데이터베이스 관련 예외를 스프링에 맞게
public class ItemRepository {
    //실제로는 HashMap 사용하면 안됨
    //그리고 스프링을 쓰기 때문에 싱글톤이 보장 되지만
    // 혹시 따로 new 해서 쓸때를 대비함
    //동시에 여러 스레드가 접근 가능하기 때문
    private  static final Map<Long,Item> store = new HashMap<>();
    //이것도 동시 접근 가능하므로 다른 거 사용해야함
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(sequence++);
        store.put(item.getId(),item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        //그냥 store.values()를 반환가능하나
        //ArrayList로 한번 감쌈 데이터 보호를 위해
        //return store.values() 그리고 이건 Collections타입임
        return new ArrayList<>(store.values());
    }

    //사실 정석적으로 하려면 updateParam을 새로운 클래스 하나 만들어서 사용해야함
    //보면 id를 사용하지 않기 때문임
    //ItemParamDTO 이런거 하나 만들어서 Item 타입 대신 넣어주는게 정석
    public void update(Long itemId,Item updateParam){
        Item findItem = findById(itemId);
        //updateParam.getId(); 이게 열려있기 때문에 이건 사용하지 않는지 혼동옴
        //프로젝트 규모가 작기 때문에 그냥 했다.
        //중복이냐 명확이냐 하면 명확한게 더 좋다
        //중복같아도 저런 ItemParamDTO를 만들어서 사용하자자
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }

}
