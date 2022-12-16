package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.AccountModel;
import com.example.model.MessageModel;
import com.example.model.MessageboardModel;
import com.example.model.Entity.Comment;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.service.SearchMessageboardService;
@Controller
public class SampleController {
	@Autowired
	SearchMessageboardService searchMessageboardService;
	@Autowired
	MessageService messageService;
	@Autowired
	AccountService accountService;
	
	// 跳轉至首頁
	@GetMapping(value = "/")
	// @ResponseBody
	public String search(Model model) {
		model.addAttribute("article", searchMessageboardService.getArticleList());
		return "index";
	}

	// 跳轉至登入頁
	@GetMapping()
	public String loginPage() {
		return "login";
	}

	// 跳轉至忘記密碼頁
	@GetMapping(value = "/password")
	public String passwardPage() {
		return "password";
	}


	@GetMapping(value = "/postmodel")
	public String postmodel(@RequestParam(value = "id", required = true) Integer id , Model model) {
		MessageboardModel MessageboardId = searchMessageboardService.getMessageboardById(id);	
		 model.addAttribute("id", MessageboardId);
		 model.addAttribute("accountservice", accountService.getAccountById(MessageboardId.getUserID()));
		 model.addAttribute("comment", messageService.getComments(id));
		    return "postmodel";
	}
	
	// 跳轉至新增貼文頁
	@GetMapping(value = "/postmessage")
	public String postmessagePage() {
		return "postmessage";//查getAttribute
	}

	// 跳轉至註冊頁
	@GetMapping(value = "/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping(value = "/add")
	@ResponseBody
	public String add(@RequestBody MessageModel messageModel) {
		messageService.saveMessage(messageModel);
		return messageModel.toString();
	}

	@PostMapping(value = "/delete")
	@ResponseBody
	public String delete(Integer deleteById) {
		deleteById = 2;
		accountService.deleteAccount(deleteById);
		return "success";

	}

	@PostMapping(value = "/addaccount")
	@ResponseBody
	public String addaccount(@RequestBody AccountModel accountModel) {
		accountService.addAccount(accountModel);
		return "success";

	}
	
	
	@PostMapping(value = "/find")
	@ResponseBody
	public String find(Integer findById) {
		findById = 4;
		List<Comment> j = messageService.getComments(findById);
		return j.toString();

	}
	
	
	

}