package jpaDgm.jpaDgm.service;

import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    @Transactional
    public void updateItem(Item item) {  itemRepository.updateItem(item); }

    @Transactional
    public void DeleteItem(Long id) { itemRepository.DeleteItem(id); }




}