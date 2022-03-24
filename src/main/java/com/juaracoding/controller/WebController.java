package com.juaracoding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.juaracoding.model.ComfortModel;
import com.juaracoding.model.ContentModel;
import com.juaracoding.model.JudulModel;
import com.juaracoding.model.TestiModel;
import com.juaracoding.model.UserModel;
import com.juaracoding.repository.ComfortRepository;
import com.juaracoding.repository.UserRepository;
import com.juaracoding.utility.FileUtility;

@Controller
public class WebController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ComfortRepository comfortRepo;
	
	JudulModel judul = new JudulModel("Juara","Mantap");
	//List<ContentModel> listContent = new ArrayList<ContentModel>();
	
	
	@GetMapping("/")
	private String index(Model model) {

		//Ini adalah pelajaran array
//		ContentModel model1 = new ContentModel("Judul ini asik", "keasikan judul ini adalah judul asik");
//		listContent.add(model1);
//		ContentModel model2 = new ContentModel("Judul ini gaasik", "gaasik karna ga rame");
//		listContent.add(model2);
//		ContentModel model3 = new ContentModel("Judul ini mantap", "mantap karna memang mantap");
//		listContent.add(model3);
//		
//		model.addAttribute("listContent", listContent);
		
		//Ini akhir dari pelajaran array
		
		
		
		TestiModel testi = new TestiModel("Banci sukses", "PT Banci Sejahtera", 
				"Tessy Wahyuni Riwayati", "/img/tessy1.jpg");

		model.addAttribute("tulisanJudul", "Memantapkan hidup seperti leri");
		
		// attribute objek
		model.addAttribute("testiObjek", testi);
		model.addAttribute("judulModel", judul);
		
		model.addAttribute("button", "Info lebih");
		return "index";
	}
	
	@GetMapping("/about")
	private String about(Model model) {
		model.addAttribute("judulModel", judul);
		return "about";
	}
	
	@GetMapping("/services")
	private String services(Model model) {
		model.addAttribute("judulModel", judul);
		model.addAttribute("listComfort", comfortRepo.findAll());
		return "services";
	}
	@GetMapping("/services/inputcomfort")
	private String servicesinput(Model model) {
		model.addAttribute("judulModel", judul);
		model.addAttribute("comfortModel", new ComfortModel());
		return "inputcomfort";
	}
	@PostMapping("/services/inputcomfort")
	private String saveComfort(@ModelAttribute ComfortModel data, @RequestParam(value="file")MultipartFile file)
		throws IOException {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "comfort-images";
		//data.setGambar("/"+uploadDir+"/"+fileName);
		data.setGambar(fileName);
		FileUtility.simpanFile(uploadDir, fileName, file);
		comfortRepo.save(data);
		return "redirect:/services";
	}
	//@PostMapping("/blog/input")
//	private String saveBlog(@ModelAttribute UserModel data, @RequestParam(value="file")MultipartFile file)
//	throws IOException{
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		String uploadDir = "user-images";
//		data.setGambar(fileName);
//		FileUtility.simpanFile(uploadDir, fileName, file);
//		userRepo.save(data);
//		return "redirect:/blog";
	
	@GetMapping("/blog")
	private String blog(Model model, @RequestParam(value="huruf", defaultValue = "")String huruf, 
		@RequestParam(value="tanggal", defaultValue = "")String tanggal){
		if(huruf.equalsIgnoreCase("")&& tanggal.equalsIgnoreCase("")) {
			model.addAttribute("listUser", userRepo.findAll());
		} else if (!tanggal.isEmpty()){
			model.addAttribute("listUser", userRepo.getUserByTanggal(tanggal));
		} else {
			model.addAttribute("listUser", userRepo.getUserByName(huruf));
		}
		
		model.addAttribute("judulModel", judul);
		//List<UserModel> listUser = new ArrayList<UserModel>();
//		UserModel user1 = new UserModel("Uzumaki Naruto", "/img/person1.jpg","hokage","kodok");
//		listUser.add(user1);
//		UserModel user2 = new UserModel("Uchiha Sasuke", "/img/person2.jpg","temennya hokage","uler");
//		listUser.add(user2);
//		UserModel user3 = new UserModel("Haruno Sakura", "/img/person3.jpg","istrinya temen hokage","siput");
//		listUser.add(user3);
//		UserModel user4 = new UserModel("Senju Tsunade", "/img/person4.jpg","cucunya hokage 1","siput juga");
//		listUser.add(user4);
//		UserModel user5 = new UserModel("Sannin Jiraiya", "/img/person5.jpg","gurunya naruto","kodok juga");
//		listUser.add(user5);
//		UserModel user6 = new UserModel("Orochimaru", "/img/person6.jpg","sanin juga temenan mreka","uler semua badan");
//		listUser.add(user6);
//		model.addAttribute("listUser", listUser);
//		model.addAttribute("listUser", userRepo.findAll());
		return "blog";
	}
	
	@GetMapping("/blog/path/{huruf}")
	private String blogg(Model model, @PathVariable("huruf")String huruf) {
		model.addAttribute("listUser", userRepo.getUserByName(huruf));
		model.addAttribute("judulModel", judul);
		return "blog";
	}
	
	@GetMapping("/blog/input")
	private String bloginput(Model model) {
		model.addAttribute("judulModel", judul);
		model.addAttribute("userModel", new UserModel());
		return "input";
	}
	
	@PostMapping("/blog/input")
	private String saveBlog(@ModelAttribute UserModel data, @RequestParam(value="file")MultipartFile file)
	throws IOException{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "user-images";
		data.setGambar(fileName);
		FileUtility.simpanFile(uploadDir, fileName, file);
		userRepo.save(data);
		return "redirect:/blog";
	}
	@GetMapping("/contact")
	private String contact(Model model) {
		model.addAttribute("judulModel", judul);
		return "contact";
	}
}
