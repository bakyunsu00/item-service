package hello.itemservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {



    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }



    @Test
    void save() {

        //given

        Item item = new Item();
        item.setItemName("book");
        item.setQuantity(10);
        item.setPrice(1000);
        //when

        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);


    }


    @Test
    void findAll() {

        //given
        Item item1 = new Item();
        item1.setItemName("book1");
        item1.setPrice(1000);
        item1.setQuantity(10);

        Item item2 = new Item();
        item2.setItemName("book2");
        item2.setPrice(2000);
        item2.setQuantity(20);

        Item findItem1 = itemRepository.save(item1);
        Item findItem2 = itemRepository.save(item2);
        //when

        List<Item> items = itemRepository.findAll();


        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(findItem1,findItem2);



    }

    @Test
    void update() {
        //given
        Item item = new Item("Book",2000,10);
        Item saved = itemRepository.save(item);
        Long itemId = saved.getId();


        //when

        Item updateParam = new Item("book2",3000,100);
        itemRepository.update(itemId,updateParam);
        Item findedItem = itemRepository.findById(itemId);

        //then

        assertThat(findedItem.getItemName()).isEqualTo(updateParam.getItemName());

    }

    @Test
    void clearStore() {
    }
}