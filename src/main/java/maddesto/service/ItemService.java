package maddesto.service;

import java.util.List;

import javax.transaction.Transactional;

import maddesto.entity.Item;
import maddesto.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public List<Item> getItems(){
		return itemRepository.findAll(new PageRequest(0, 10, Direction.DESC, "publishDate")).getContent();
	}

}
