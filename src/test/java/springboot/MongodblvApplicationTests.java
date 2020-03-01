package springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.Entity.User;
import springboot.service.primary.IUserService;
import springboot.service.secondary.IUserService2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodblvApplicationTests {

	@Value("${spring.data.mongodb.conllection}")
	private String collectionName;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserService2 userService2;
	@Test
	public void contextLoads() {
	}

	@Test
	public void save() {
		User user = new User();
		user.setId(2l);
		user.setUserName("ltj");
		user.setPassWord("123123");
		userService.save(user, collectionName);
	}

	@Test
	public void save2() {
		User user = new User();
		user.setId(2l);
		user.setUserName("ltj");
		user.setPassWord("123123");
		userService2.save(user, collectionName);
	}

	@Test
	public void findByName() {
		Query query = new Query();
		query.addCriteria(new Criteria("userName").is("ltj"));
		System.out.println(userService.findByName(query, User.class, collectionName));
	}

	@Test
	public void findByName2() {
		Query query = new Query();
		query.addCriteria(new Criteria("userName").is("ltj"));
		System.out.println(userService2.findByName(query, User.class, collectionName));
	}

	@Test
	public void update() {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(3));
		Update update = new Update();
		update.set("userName", "lsj").set("passWord", "123");
		userService.update(query, update, User.class, collectionName);
	}

	@Test
	public void count() {
		System.out.println(userService.count(null, User.class, collectionName));
	}

	@Test
	public void delete() {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(2));
		userService.delete(query, User.class, collectionName);
	}

}
