package com.restbackend.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restbackend.model.Menu;
import com.restbackend.model.MenuRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api4")
public class MenuController implements CommandLineRunner{
	
	
	@Autowired
	private MenuRepository dao;
	
	
	@GetMapping("/items")
	public List<Menu> getAllMenu(){
		return dao.findAll();
	}
	
	@GetMapping("/item/{menuId}")
	public Menu getByMenuId(@PathVariable Integer menuId) {
		System.out.println("MID="+menuId);
		return dao.findById(menuId).orElseThrow(() -> new RuntimeException("Menu item not found with ID: " + menuId));
	}
	
	
	@PostMapping("/item")
	public Menu createMenu(@RequestBody Menu newMenu) {
	    return dao.save(newMenu);
	}
		
	
	@PutMapping("/item/{menuId}")
	public Menu updateMenu(@PathVariable Integer menuId,@RequestBody Menu updateMenu) {
		Menu exsitMenu = dao.findById(menuId).orElseThrow(()-> new RuntimeException("Menu item not found with ID: " + menuId));
		exsitMenu.setMenu_name(updateMenu.getMenu_name());
		exsitMenu.setMenu_price(updateMenu.getMenu_price());
		exsitMenu.setMenu_describe(updateMenu.getMenu_describe());
		exsitMenu.setMenu_image(updateMenu.getMenu_image());
		exsitMenu.setMenu_states(updateMenu.getMenu_states());
		
		return dao.save(exsitMenu);
	}
	
//	@PutMapping("/item/{menuId}")
//	public Menu updateMenu(@PathVariable Integer menuId, @RequestParam("menu_name") String menuName,
//	                       @RequestParam("menu_price") Integer menuPrice,
//	                       @RequestParam("menu_describe") String menuDescribe,
//	                       @RequestParam("menu_states") Boolean menuStates,
//	                       @RequestParam(value = "menu_image", required = false) MultipartFile file) {
//	    Menu existingMenu = dao.findById(menuId)
//	            .orElseThrow(() -> new RuntimeException("Menu item not found with ID: " + menuId));
//	    existingMenu.setMenu_name(menuName);
//	    existingMenu.setMenu_price(menuPrice);
//	    existingMenu.setMenu_describe(menuDescribe);
//	    existingMenu.setMenu_states(menuStates);
//
//	    if (file != null && !file.isEmpty()) {
//	        String fileName = file.getOriginalFilename();
//	        String filePath = "/path/to/save/" + fileName;  // 替換成實際的路徑
//	        try {
//	            file.transferTo(new File(filePath));
//	            existingMenu.setMenu_image(filePath); // 或是只設置檔案名稱
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    System.out.println("x=>"+existingMenu);
//	    return dao.save(existingMenu);
//	}
//	
	
	
	   @DeleteMapping("/item/{menuId}")
	    public void deleteMenu(@PathVariable Integer menuId) {
	        Menu exsitMenu = dao.findById(menuId)
	            .orElseThrow(() -> new RuntimeException("Menu item not found with ID: " + menuId));
	        dao.delete(exsitMenu);
	    }
	

	@Override
	public void run(String... args) throws Exception {
		dao.save(new Menu(1, "滷肉飯", 50, "滷肉飯是台灣經典的小吃，以豬肉滷汁淋在米飯上，香味四溢，是台灣人的最愛。", "./img/snack1.jpg",true));
		dao.save(new Menu(2, "蚵仔煎", 70, "蚵仔煎是一道經典台灣小吃，裡面包著新鮮的蚵仔和蛋，再搭配特製的醬汁。", "./img/snack2.jpg",true));
		dao.save(new Menu(3, "牛肉麵", 120, "台灣牛肉麵以濃郁的湯底和軟嫩的牛肉聞名，搭配手工麵條，美味無比。", "./img/snack3.jpg",true));
		dao.save(new Menu(4, "雞排", 60, "台灣夜市人氣美食，外酥內嫩的雞排，搭配獨家醃料，讓人一口接一口。", "./img/snack4.jpg",true));
		dao.save(new Menu(5, "豆花", 40, "豆花是台灣的傳統甜品，細膩滑嫩的豆花配上多種配料，清涼爽口。", "./img/snack5.jpg",true));
		dao.save(new Menu(6, "鹹酥雞", 80, "鹹酥雞是台灣夜市不可錯過的炸物，酥脆多汁，配上九層塔更是絕配。", "./img/snack6.jpg",true));
		dao.save(new Menu(7, "燒餅油條", 30, "燒餅油條是台灣經典早餐組合，香脆的燒餅夾著酥脆的油條，滿足一早的味蕾。", "./img/snack7.jpg",true));
		dao.save(new Menu(8, "紅豆餅", 20, "外皮酥脆、內餡甜美的紅豆餅，是台灣街頭常見的小吃，口感細膩。", "./img/snack8.jpg",true));
		dao.save(new Menu(9, "臭豆腐", 50, "臭豆腐以獨特的氣味和酥脆的外皮著稱，是台灣夜市的代表小吃之一。", "./img/snack9.jpg",true));
		dao.save(new Menu(10, "牛舌餅", 35, "牛舌餅是一種外皮香酥、內餡豐富的傳統小吃，通常以牛舌形狀命名。", "./img/snack10.jpg",true));
		dao.save(new Menu(11, "米血糕", 40, "米血糕以豬血和糯米製成，口感獨特，是台灣夜市中的傳統美食。", "./img/snack11.jpg",true));
		dao.save(new Menu(12, "甜不辣", 50, "甜不辣是一道以魚漿製成的小吃，搭配各種醬料，軟嫩有嚼勁。", "./img/snack12.jpg",true));
		dao.save(new Menu(13, "蔥抓餅", 40, "蔥抓餅是台灣街頭常見的麵食，外酥內軟，層次分明，搭配蔥香味十足。", "./img/snack13.jpg",true));
		dao.save(new Menu(14, "麻辣鍋", 150, "麻辣鍋以其辛辣的湯底和豐富的配料聞名，是冬天暖胃的不二之選。", "./img/snack14.jpg",true));
		dao.save(new Menu(15, "擔仔麵", 60, "擔仔麵是台灣的一道傳統麵食，以香濃的湯底和Q彈的麵條著稱。", "./img/snack15.jpg",true));
		
	}

}
