package maddesto.service;

import java.util.List;

import maddesto.entity.Blog;
import maddesto.entity.Item;
import maddesto.entity.User;
import maddesto.exception.RssException;
import maddesto.repository.BlogRepository;
import maddesto.repository.ItemRepository;
import maddesto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void saveItems(Blog blog){
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for(Item item: items){
				Item itemFormDb = itemRepository.findByBlogAndLink(blog, item.getLink());
				if(itemFormDb == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Scheduled(fixedDelay=3600000) //one hour
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for(Blog blog: blogs){
			saveItems(blog);
		}
	}
	
	public void save(Blog blog, String username){
		User user = userRepository.findByName(username);
		blog.setUser(user);
		blogRepository.save(blog); 
		saveItems(blog);
	}

	@PreAuthorize("(#blog.user.name == authentication.name) or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);		
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
		
	}

}
