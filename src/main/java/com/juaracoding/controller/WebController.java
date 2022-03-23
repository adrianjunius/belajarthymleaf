package com.juaracoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juaracoding.model.ContentModel;
import com.juaracoding.model.JudulModel;
import com.juaracoding.model.TestiModel;
import com.juaracoding.model.UserModel;

@Controller
public class WebController {

	JudulModel judul = new JudulModel("Juara","Mantap");
	List<ContentModel> listContent = new ArrayList<ContentModel>();
	List<UserModel> listUser = new ArrayList<UserModel>();
	
	@GetMapping("/")
	private String index(Model model) {

		//Ini adalah pelajaran array
		ContentModel model1 = new ContentModel("Judul ini asik", "keasikan judul ini adalah judul asik");
		listContent.add(model1);
		ContentModel model2 = new ContentModel("Judul ini gaasik", "gaasik karna ga rame");
		listContent.add(model2);
		ContentModel model3 = new ContentModel("Judul ini mantap", "mantap karna memang mantap");
		listContent.add(model3);
		
		model.addAttribute("listContent", listContent);
		
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
		return "services";
	}
	
	@GetMapping("/blog")
	private String blog(Model model) {
		model.addAttribute("judulModel", judul);
		
		UserModel user1 = new UserModel("Uzumaki Naruto", "/img/person1.jpg","hokage","kodok");
		listUser.add(user1);
		UserModel user2 = new UserModel("Uchiha Sasuke", "/img/person2.jpg","temennya hokage","uler");
		listUser.add(user2);
		UserModel user3 = new UserModel("Haruno Sakura", "/img/person3.jpg","istrinya temen hokage","siput");
		listUser.add(user3);
		UserModel user4 = new UserModel("Senju Tsunade", "/img/person4.jpg","cucunya hokage 1","siput juga");
		listUser.add(user4);
		UserModel user5 = new UserModel("Sannin Jiraiya", "/img/person5.jpg","gurunya naruto","kodok juga");
		listUser.add(user5);
		UserModel user6 = new UserModel("Orochimaru", "/img/person6.jpg","sanin juga temenan mreka","uler semua badan");
		listUser.add(user6);
		model.addAttribute("listUser", listUser);
		return "blog";
	}
	
	@GetMapping("/contact")
	private String contact(Model model) {
		model.addAttribute("judulModel", judul);
		return "contact";
	}
}
