package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.LongSummaryStatistics;

@Controller
@RequestMapping("/basic/items")
@Slf4j
//롬복임
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    //목록 출력
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();

        model.addAttribute("items",items);
        //논리명 주소임
        return "basic/items";
    }

    //상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model){
        //Item item = new Item(itemName, price, quantity);
        //생성자로 해도 됨 @ModelAttribute와 비교하기 위함
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        log.info("itemName = {},price = {},quantity = {}",itemName,price,quantity);

        model.addAttribute("item",item);

        //이제 저장된 결과를 상세한 저장 결과로 보여주고 싶음
        //view를 새로 만들지 않고 item.html을 통해서 상세하게 보여주자
        //그래서 그냥 model에 담아주기만 하면 된다


       return "basic/item";
    }

    //@PostMapping("/add")
    //이 @ModelAttribute는 용도도 여러가지임
    //여기서는 두가지를 같이 처리하는 기능에 집중
    //모델 객체를 만들어주고 담아놓은 모델 객체는 view에서도 쓰이므로 view에서도 사용가능
    //model.addAttribute("item",item); 이걸 자동으로 해줌
    //@ModelAttribute("item") 여기안에 지정된 이름으로 addAttribute에 "item"으로 넣어줌
    public String addItemV2(@ModelAttribute("item") Item item){

        //ModelAttribute가 대신 만들어줌줌
//       Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
        itemRepository.save(item);
        log.info("itemName = {},price = {},quantity = {}",item.getItemName(),item.getPrice(),item.getQuantity());

        //이거 주석처리 가능
        //model.addAttribute("item",item);

        //이제 저장된 결과를 상세한 저장 결과로 보여주고 싶음
        //view를 새로 만들지 않고 item.html을 통해서 상세하게 보여주자
        //그래서 그냥 model에 담아주기만 하면 된다


        return "basic/item";
    }

    //@PostMapping("/add")
    //이럴경우 Item 클래스의 첫글자만 소문자로 바꿔서 "item" 으로 넘겨줌
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    //이럴경우 Item 클래스의 첫글자만 소문자로 바꿔서 "item" 으로 넘겨줌
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    //이럴경우 Item 클래스의 첫글자만 소문자로 바꿔서 "item" 으로 넘겨줌
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    //이럴경우 Item 클래스의 첫글자만 소문자로 바꿔서 "item" 으로 넘겨줌
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.save(item);
                                                    //이 값이
        redirectAttributes.addAttribute("itemId",saveItem.getId());
        //그리고 이런 남는 애들은 쿼리 파라미터로 들어감
        redirectAttributes.addAttribute("status",true);
                                        //이걸로 치환됨
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);

        //redirect
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }

}
