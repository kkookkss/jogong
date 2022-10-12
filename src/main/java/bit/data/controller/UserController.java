package bit.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;

import bit.data.dto.UserDto;
import bit.data.service.UserService;
import bit.data.service.UserServiceInter;

@Controller
public class UserController {
	
	@Autowired
	UserServiceInter userServiceInter;
	
	@GetMapping("/user/friendData")
	@ResponseBody
	public List<UserDto> getUserFriendData(int userNum) {
		return userServiceInter.getUserFriendData(userNum);
	}
	
	@GetMapping("/user/friendCount")
	@ResponseBody
	public int getUserFriendCount(int userNum) {
		return userServiceInter.getUserFriendCount(userNum);
	}
	
	@GetMapping("/mypage/cart")
	public String cartList() {
	    return "/mypage/mypage/cart";
	}
	

    @GetMapping("/mypage/user")
    public String userPage(HttpSession session,Model model) {
    	String email = (String) session.getAttribute("loginid");
    	UserDto list= userServiceInter.getDataById(email);
    	model.addAttribute("list", list);
    	model.addAttribute("birthday", list.getDate().toString());
    	model.addAttribute("addressNumber", list.getAddress().substring(0, 5));
    	model.addAttribute("address", list.getAddress().substring(5));
    	
        return "/mypage/mypage/user";
    }
    @GetMapping("/mypage/qna")
    public String qnaPage() {
        return "/mypage/mypage/qna";
    }

	@GetMapping("/user/review")
	@ResponseBody
	public List<UserDto> getReviewUserInfo(){
		
		return userServiceInter.getReviewUserInfo();
	}

}



