package maddesto.service;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import maddesto.entity.Blog;
import maddesto.entity.Item;
import maddesto.entity.Role;
import maddesto.entity.User;
import maddesto.repository.BlogRepository;
import maddesto.repository.ItemRepository;
import maddesto.repository.RoleRepository;
import maddesto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PostConstruct
	public void init(){
		if(roleRepository.findByName("ROLE_ADMIN") == null){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		userAdmin.setPassword(encoder.encode("admin"));
		
		List<Role> adminRoles = new ArrayList<Role>();
		adminRoles.add(roleUser);
		adminRoles.add(roleAdmin);
		
		userAdmin.setRoles(adminRoles);
		
		userRepository.save(userAdmin);
		
		Blog blog = new Blog();
		blog.setName("TestBlog");
		blog.setUrl("http://feeds.feedburner.com/javavids");
		//blog.setUrl("http://youtube.com");
		blog.setUser(userAdmin);
		blogRepository.save(blog);
		
//		Item item1 = new Item();
//		item1.setBlog(blog);
//		item1.setDescription("Description for item1");
//		item1.setTitle("first item");
//		item1.setPublishDate(new Date());
//		item1.setLink("https://www.youtube.com");
//		itemRepository.save(item1);
//		
//		Item item2 = new Item();                  
//		item2.setBlog(blog);                      
//		item2.setDescription("Description for item2");
//		item2.setPublishDate(new Date()); 
//		item2.setTitle("second title");
//		item2.setLink("https://www.youtube.com"); 
//		itemRepository.save(item2);
		}
		
	}

}
