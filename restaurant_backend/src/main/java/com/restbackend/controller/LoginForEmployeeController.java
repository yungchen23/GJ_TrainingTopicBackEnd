package com.restbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restbackend.model.Employee;
import com.restbackend.model.EmployeeRepository;
import com.restbackend.model.LoginForEmployee;
import com.restbackend.model.LoginForEmployeeRepository;
import com.restbackend.model.SessionInfo;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LoginForEmployeeController implements CommandLineRunner {

	@Autowired
	private LoginForEmployeeRepository dao;
	
	@Autowired
	private EmployeeRepository dao2;
	
	private Map<String, SessionInfo> sessionStore = new HashMap<>();
	
	
	
	@GetMapping("/all")
	public List<LoginForEmployee> getAllEmployees() {
		return dao.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForEmployee loginData) {
		
		LoginForEmployee user = dao.findByLoginAccount(loginData.getLogin_account());
		Employee emp = dao2.findByLoginAccount(loginData.getLogin_account());
		
		System.out.println("user->"+user);
		System.out.println("emp->"+emp);
		
		if (user != null && user.getLogin_password().equals(loginData.getLogin_password())) {
			
			 if (user.getEmployee_state()) {
	                // 生成一個Session ID
	                String sessionId = UUID.randomUUID().toString();
	                
	                
	                // 創建SessionInfo對象，並存儲到sessionStore中
	                SessionInfo sessionInfo = new SessionInfo(user.getLogin_account(), emp.getEmployee_name());
	                sessionStore.put(sessionId, sessionInfo);
	                
//	                sessionStore.put(sessionId, user.getLogin_account());
//	                sessionStore.put(sessionId, emp.getEmployee_name());
//					
	                // 打印所有Session信息
	                for (Map.Entry<String,SessionInfo> x : sessionStore.entrySet()) {
						String key = x.getKey();
						SessionInfo value = x.getValue();
						System.out.println("key:"+key+"--"+"value:"+value);
					}
	                
	                
	                // 返回Session ID给前端
	                return ResponseEntity.ok(Map.of("success", true, "sessionId", sessionId));
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                        .body(Map.of("success", false, "message", "帳號已停用"));
	            }
		} else {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "帳號密碼錯誤"));
		}
	}
	
	@GetMapping("/session")
    public ResponseEntity<?> checkSession(@RequestHeader("X-Session-Id") String sessionId) {
		SessionInfo user = sessionStore.get(sessionId);
//        System.out.println("sessionId->"+sessionId);
//        System.out.println("session->"+user);
        if (user != null) {
            return ResponseEntity.ok(Map.of("user", user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "用戶未登入"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("X-Session-Id") String sessionId) {
        sessionStore.remove(sessionId);
        return ResponseEntity.ok(Map.of("message", "登出成功"));
    }
	
	
	
	
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginForEmployee loginData/*, HttpSession session*/) {
//
//		LoginForEmployee user = dao.findByLoginAccount(loginData.getLogin_account());
//
//		if (user != null && user.getLogin_password().equals(loginData.getLogin_password())) {
//
//			if (user.getEmployee_state() == true) {
//				System.out.println("XXXX=>" + user);
//				session.setAttribute("user", user);
//				System.out.println("ssss=>" + user);
//				return ResponseEntity.ok(Map.of("success", true, "message", "登入成功"));
//			} else {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//						.body(Map.of("success", false, "message", "帳號已停用"));
//			}
//		} else {
//
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "帳號密碼錯誤"));
//		}
//	}
//
//	@PostMapping("/logout")
//	public ResponseEntity<?> logout(HttpSession session) {
//		session.invalidate(); // 清除 session
//		return ResponseEntity.ok(Map.of("message", "登出成功"));
//	}
//
//	@GetMapping("/session")
//	public ResponseEntity<?> checkSession(HttpSession session) {
//		LoginForEmployee user = (LoginForEmployee) session.getAttribute("user");
//		System.out.println("YYY=>"+user);
//		if (user != null) {
//			return ResponseEntity.ok(Map.of("user", user));
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "用戶未登入"));
//		}
//	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees2() {
		return dao2.findAll();
	}

	@GetMapping("/employee/{loginAccount}")
	public Employee getEmployeeByLoginAccount(@PathVariable String loginAccount) {
		return dao2.findByLoginAccount (loginAccount);

	}

	// 接收JSON數據並更新DB
	@PutMapping("/employee/{loginAccount}")
	public Employee updateEmployee(@PathVariable String loginAccount, @RequestBody Employee updatedEmployee) {
		Employee existEmployee = dao2.findByLoginAccount(loginAccount);

		if (existEmployee != null) {

			// 更新現有員工紀錄
			existEmployee.setEmployee_name(updatedEmployee.getEmployee_name());
			existEmployee.setEmployee_position(updatedEmployee.getEmployee_position());
			existEmployee.setEmployee_jointime(updatedEmployee.getEmployee_jointime());
			existEmployee.setEmployee_phone(updatedEmployee.getEmployee_phone());
			existEmployee.setEmployee_birth(updatedEmployee.getEmployee_birth());
			existEmployee.setEmployee_mail(updatedEmployee.getEmployee_mail());

			// 更新LoginForEmployee物件
			LoginForEmployee loginForEmployee = existEmployee.getLoginForEmployee();
			loginForEmployee.setLogin_account(updatedEmployee.getLoginForEmployee().getLogin_account());
			loginForEmployee.setLogin_password(updatedEmployee.getLoginForEmployee().getLogin_password());
			loginForEmployee.setEmployee_state(updatedEmployee.getLoginForEmployee().getEmployee_state());

			// 保存
			dao2.save(existEmployee);

			return existEmployee;
		} else {

			return null;
		}
	}

	@Override
	public void run(String... args) throws Exception {
//		LoginForEmployee login1 =dao.save(new LoginForEmployee(1, "wang@hh.com", "123", true));
//		LoginForEmployee login2 =dao.save(new LoginForEmployee(2, "li@hh.com", "123", false));
//		LoginForEmployee login3 =dao.save(new LoginForEmployee(3, "wu@hh.com", "123", true));
//		LoginForEmployee login4 =dao.save(new LoginForEmployee(4, "chen@hh.com", "123", true));
//		dao2.save(new Employee(login1, "王大明", "經理", "2022-01-01", "123456789", "1990-01-01", "wang@hh.com"));
//		dao2.save(new Employee(login2, "李小美", "職員", "2022-02-01", "987654321", "1992-02-02", "li@hh.com"));
//		dao2.save(new Employee(login3, "吳忠信", "主管", "2022-03-01", "567894321", "1985-03-03", "wu@hh.com"));
//		dao2.save(new Employee(login4, "陳大偉", "職員", "2022-04-01", "123498765", "1988-04-04", "chen@hh.com"));

		dao.save(new LoginForEmployee(1, "wang@hh.com", "123", true));
		dao.save(new LoginForEmployee(2, "li@hh.com", "123", false));
		dao.save(new LoginForEmployee(3, "wu@hh.com", "123", true));
		dao.save(new LoginForEmployee(4, "chen@hh.com", "123", true));

		dao2.save(new Employee(1, "王大明", "經理", "2022-01-01", "123456789", "1990-01-01", "wang@hh.com"));
		dao2.save(new Employee(2, "李小美", "職員", "2022-02-01", "987654321", "1992-02-02", "li@hh.com"));
		dao2.save(new Employee(3, "吳忠信", "主管", "2022-03-01", "567894321", "1985-03-03", "wu@hh.com"));
		dao2.save(new Employee(4, "陳大偉", "職員", "2022-04-01", "123498765", "1988-04-04", "chen@hh.com"));
	}
}
