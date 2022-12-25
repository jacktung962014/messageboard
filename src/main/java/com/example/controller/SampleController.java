package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.AccountModel;
import com.example.model.MessageModel;
import com.example.model.MessageboardModel;
import com.example.model.Entity.UserAccount;
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
	public String search(Model model, HttpSession session) {
		// <-- 文章列表 Start -->
		model.addAttribute("article", searchMessageboardService.getArticleList());
		// <-- 文章列表 End -->
		// <-- 顯示使用者名稱 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;
		// 如果username為空(未登入)，顯示為訪客
		try {
			username = userAccount.getUsername();// 查詢值，結果為null會丟出例外
		} catch (Exception e) {
			username = "訪客";// 丟出例外時將username設為訪客
		}
		model.addAttribute("user", username);
		// <-- 顯示使用者名稱 End -->
		return "index";
	}

	// 跳轉至登入頁
	@GetMapping(value = "/login")
	public String loginPage(@ModelAttribute UserAccount userAccount) {
		return "login";
	}

	// 登入判斷+Session+跳轉回首頁
	@PostMapping(value = "/login")
	public String loginSession(@ModelAttribute UserAccount userAccount, HttpSession session) {
		// <-- 登入判斷 Start -->
		String checkuser = userAccount.getAccount();// 取得帳號
		String checkpassword = userAccount.getPassword();// 取得密碼
		// try:比對輸入的帳號密碼是否與資料庫內的帳號密碼符合 catch:處理資料庫出錯(如回傳null)等例外，出錯後導向回登入頁(暫時)
		try { // 查詢值，結果為null會丟出例外
			Boolean a = accountService.getIdentityByAccount(userAccount.getAccount()).getAccount().equals(checkuser);
			Boolean b = accountService.getIdentityByAccount(userAccount.getAccount()).getPassword()
					.equals(checkpassword);
			// if else:輸入的帳號密碼相符，導向回首頁，不符導向回登入頁
			if (a.equals(b)) {
				// 將使用者資訊放入userAccount物件以供使用
				String setUsernameIfCorrect = accountService.getIdentityByAccount(userAccount.getAccount())
						.getUsername();
				Integer setPermissionIfCorrect = accountService.getIdentityByAccount(userAccount.getAccount())
						.getPermission();
				Integer setUserIDIfCorrect = accountService.getIdentityByAccount(userAccount.getAccount()).getId();
				userAccount.setUsername(setUsernameIfCorrect);
				userAccount.setPermission(setPermissionIfCorrect);
				userAccount.setUserID(setUserIDIfCorrect);
				// 將userAccount物件的值放入session以供存取
				session.setAttribute("getUserAccount", userAccount);
				return "redirect:/";
			} else {
				return "redirect:/register";
			}
		} catch (Exception e) {
			return "redirect:/register";// 丟出例外時導向回註冊頁(暫時)
		}
		// <-- 登入判斷 End -->
	}

	// 跳轉至忘記密碼頁
	@GetMapping(value = "/password")
	public String passwardPage() {
		return "password";
	}

	// 跳轉至貼文頁
	@GetMapping(value = "/postmodel")
	public String postModel(@RequestParam(value = "id", required = true) Integer id, Model model, HttpSession session,
			@ModelAttribute MessageModel messageModel) {

		// <-- 顯示使用者名稱 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;
		// 如果username為空(未登入)，顯示為訪客
		try {
			username = userAccount.getUsername();// 查詢值，結果為null會丟出例外
		} catch (Exception e) {
			username = "訪客";// 丟出例外時將username設為訪客
		}
		model.addAttribute("user", username);
		// <-- 顯示使用者名稱 End -->

		// <-- 顯示貼文內容及留言 Start -->
		MessageboardModel messageboardId = searchMessageboardService.getMessageboardById(id);
		model.addAttribute("id", messageboardId);// 取出messageboard表的資料
		model.addAttribute("userinfo", accountService.getAccountById(messageboardId.getUserID()));// 取出account表的資料
		model.addAttribute("comment", messageService.getComments(id));// 取出message表的資料，塞入Comments實體
		return "postmodel";
		// <-- 顯示貼文內容及留言 End -->
	}

	// 在貼文頁中新增留言
	@PostMapping(value = "/postmodel")
	public String postComments(@RequestParam Map<String, String> requestParams, @ModelAttribute MessageModel messageModel, HttpSession session) {

		
		
		
		String ids = requestParams.get("?id=");
		System.out.println(ids);
		
		
		// <-- 顯示使用者名稱+登入檢查 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;
		
		try {
			username = userAccount.getUsername();// 查詢值，結果為null會丟出例外
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/login";// 丟出例外時(未登入)將重新導向至登入頁
		}
		// <-- 顯示使用者名稱+登入檢查 End -->
	}

	// 跳轉至新增貼文頁
	@GetMapping(value = "/postmessage")
	public String postMessagePage(@ModelAttribute MessageboardModel messageboardModel, Model model,
			HttpSession session) {
		// <-- 顯示使用者名稱+登入檢查 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;
		try {
			username = userAccount.getUsername();// 查詢值，結果為null會丟出例外
			model.addAttribute("user", username);// 如果username不為空(已登入)，顯示使用者名稱
			return "postmessage";
		} catch (Exception e) {
			return "redirect:/login";// 丟出例外時(未登入)將重新導向至登入頁
		}
		// <-- 顯示使用者名稱+登入檢查 End -->
	}

	// 新增貼文至資料庫
	@PostMapping(value = "/postmessage")
	public String postMessageNow(@ModelAttribute MessageboardModel messageboardModel, HttpSession session) {
		// <-- 新增貼文 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");// 取得儲存的Session並強制轉型成UserAccount實體
		Integer UserID = userAccount.getUserID();// 自userAccount實體取得UserID
		messageboardModel.setUserID(UserID);
		searchMessageboardService.saveMessageboard(messageboardModel);
		// <-- 新增貼文 End -->
		return "redirect:/";
	}

	// 跳轉至註冊頁
	@GetMapping(value = "/register")
	public String registerPage(@Valid Model model) {
		model.addAttribute("accountModel", new AccountModel());// 因為Thymeleaf需要放個AccountModel避免報錯
		return "register";
	}

	// 註冊頁傳值至後端、MySQL儲存
	@PostMapping(value = "/register")
	public String addUser(@Valid AccountModel accountModel, BindingResult bindingResult, Model model) {
		// <-- 新增帳號 Start -->
		if (bindingResult.hasErrors()) {// 如果有報錯，跳轉至register頁面
			List<ObjectError> errorList = bindingResult.getAllErrors();
			model.addAttribute("error", errorList);
			for (int i = 0; i < errorList.size(); i++) {
				System.out.println(errorList.get(i));
			}
			return "redirect:/register";
		}
		accountService.addAccount(accountModel);
		return "redirect:/";// 重新導向頁面，使用 redirect 關鍵字，搭配絕對路徑，這邊表示會回到根路徑。
		// <-- 新增帳號 End -->
	}

	// 測試用API，為postman測試用，非正式網頁用。
	@PostMapping(value = "/find?id=2")
	@ResponseBody
	public Map<String, Object> findall(String id) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", id);
		System.out.println(paraMap);
		return paraMap;
	}
}