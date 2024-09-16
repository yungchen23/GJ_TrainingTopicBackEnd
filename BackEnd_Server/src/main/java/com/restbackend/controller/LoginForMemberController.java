package com.restbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restbackend.model.Employee;
import com.restbackend.model.LoginForMember;
import com.restbackend.model.LoginForMemberRepository;
import com.restbackend.model.Member;
import com.restbackend.model.MemberRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api2")
public class LoginForMemberController implements CommandLineRunner {

	@Autowired
	private LoginForMemberRepository dao;

	@Autowired
	private MemberRepository dao2;

	@GetMapping("/memberlogin")
	public List<LoginForMember> getAllMember() {
		return dao.findAll();

	}

	@GetMapping("/members")
	public List<Member> getAllEmployees2() {
		return dao2.findAll();
	}
		

	@GetMapping("/member/{loginAccount}")
	public Member getMemberByLoginAccount(@PathVariable String loginAccount) {
		return dao2.findByLoginAccount(loginAccount);
	}

	@PutMapping("/member/{loginAccount}")
	public Member updateMember(@PathVariable String loginAccount, @RequestBody Member updateMember) {
		Member existMember = dao2.findByLoginAccount(loginAccount);

		if (existMember != null) {

			// 更新現有客戶紀錄
			existMember.setMember_name(updateMember.getMember_name());
			existMember.setMember_sex(updateMember.getMember_sex());
			existMember.setMember_phone(updateMember.getMember_phone());
			existMember.setMember_birth(updateMember.getMember_birth());
			existMember.setMember_mail(updateMember.getMember_mail());
			existMember.setMember_address(updateMember.getMember_address());

			// 更新loginformember物件
			LoginForMember loginformember = existMember.getLoginformember();
			loginformember.setLogin_id(updateMember.getLoginformember().getLogin_id());
			loginformember.setLogin_account(updateMember.getLoginformember().getLogin_account());			
//			loginformember.setLogin_password(updateMember.getLoginformember().getLogin_password());
//			loginformember.setLogin_question(updateMember.getLoginformember().getLogin_question());
//			loginformember.setLogin_answer(updateMember.getLoginformember().getLogin_answer());
			
			loginformember.setMember_state(updateMember.getLoginformember().getMember_state());
			
			
			
			dao2.save(existMember);

			return existMember;

		} else {
			return null;
		}

	}

	@Override
	public void run(String... args) throws Exception {
		dao.save(new LoginForMember(1, "wang@example.com", "123", "你小時候好朋友的名字?", "AAA", true));
		dao.save(new LoginForMember(2, "li@example.com", "123", "你父母第一次約會的地方?", "BBB", true));
		dao.save(new LoginForMember(3, "chen@example.com", "123", "你小學的校名?", "CCC", true));
		dao.save(new LoginForMember(4, "lin@example.com", "123", "你的偶像的名字?", "AAA", true));
		dao.save(new LoginForMember(5, "huang@example.com", "123", "你第一次寵物是什麼?", "BBB", true));
		dao.save(new LoginForMember(6, "wu@example.com", "123", "你最喜歡的流行歌曲?", "CCC", true));
		dao.save(new LoginForMember(7, "tsai@example.com", "123", "你最喜歡的旅遊景點?", "AAA", true));
		dao.save(new LoginForMember(8, "chang@example.com", "123", "你的初戀是在幾歲?", "BBB", false));
		dao.save(new LoginForMember(9, "linzl@example.com", "123", "你小時候好朋友的名字?", "CCC", true));
		dao.save(new LoginForMember(10, "wuzx@example.com", "123", "你父母第一次約會的地方?", "AAA", true));
		dao.save(new LoginForMember(11, "chengkw@example.com", "123", "你小學的校名?", "BBB", true));
		dao.save(new LoginForMember(12, "chienwb@example.com", "123", "你的偶像的名字?", "CCC", true));
		dao.save(new LoginForMember(13, "tsengyn@example.com", "123", "你第一次寵物是什麼?", "AAA", true));
		dao.save(new LoginForMember(14, "liaowz@example.com", "123", "你最喜歡的流行歌曲?", "BBB", false));
		dao.save(new LoginForMember(15, "chungxq@example.com", "123", "你最喜歡的旅遊景點?", "CCC", true));

//		dao2.save(new Member(1,"AA王大明","先生","123","wang@example.com","1985-03-21","台北市"));

		dao2.save(new Member(1, "王大明", "先生", "0912345678", "wang@example.com", "1985-03-21", "台北市"));
		dao2.save(new Member(2, "李小美", "小姐", "0987654321", "li@example.com", "1990-05-15", "新北市"));
		dao2.save(new Member(3, "陳志強", "先生", "0976543210", "chen@example.com", "1978-12-01", "桃園市"));
		dao2.save(new Member(4, "林雅婷", "小姐", "0954321098", "lin@example.com", "1988-08-09", "台中市"));
		dao2.save(new Member(5, "黃宗翰", "先生", "0932109876", "huang@example.com", "1992-02-28", "高雄市"));
		dao2.save(new Member(6, "吳依婷", "小姐", "0921098765", "wu@example.com", "1995-07-19", "台南市"));
		dao2.save(new Member(7, "蔡志成", "先生", "0911098765", "tsai@example.com", "1983-11-23", "基隆市"));
		dao2.save(new Member(8, "張靜宜", "小姐", "0910987654", "chang@example.com", "1987-10-10", "新竹市"));
		dao2.save(new Member(9, "林志玲", "小姐", "0932987654", "linzl@example.com", "1980-04-05", "嘉義市"));
		dao2.save(new Member(10, "吳宗憲", "先生", "0987654321", "wuzx@example.com", "1975-06-15", "台東市"));
		dao2.save(new Member(11, "鄭愷文", "先生", "0912345678", "chengkw@example.com", "1982-09-22", "苗栗縣"));
		dao2.save(new Member(12, "簡文彬", "先生", "0932987654", "chienwb@example.com", "1989-01-30", "屏東縣"));
		dao2.save(new Member(13, "曾雅妮", "小姐", "0954321098", "tsengyn@example.com", "1993-03-14", "宜蘭縣"));
		dao2.save(new Member(14, "廖文中", "先生", "0911098765", "liaowz@example.com", "1981-02-17", "花蓮縣"));
		dao2.save(new Member(15, "鍾小琴", "小姐", "0910987654", "chungxq@example.com", "1990-12-25", "南投縣"));

	}

}
