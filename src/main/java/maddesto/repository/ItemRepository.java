package maddesto.repository;

import java.util.List;

import maddesto.entity.Blog;
import maddesto.entity.Item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	public List<Item> findByBlog(Blog blog, Pageable pagable);
	
	public Item findByBlogAndLink(Blog blog, String link);
}
