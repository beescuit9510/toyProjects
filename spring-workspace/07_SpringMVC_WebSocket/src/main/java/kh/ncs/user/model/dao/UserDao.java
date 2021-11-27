package kh.ncs.user.model.dao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserDao {

	@RequestMapping(value="/a.do")
	public void loginUser() {
	
	 System.out.println("aaa");
	
	}
}