package com.example.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.UserAccount;
import com.example.model.Entity.AccountModel;
import com.example.model.Entity.MessageModel;
import com.example.model.Entity.MessageboardModel;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.service.SearchMessageboardService;

@Validated
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
		String username;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		// 如果username為空(未登入)，顯示為訪客
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
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
			BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();// 新建BCryptPasswordEncoder，用於比對密碼
			Boolean a = accountService.getIdentityByAccount(userAccount.getAccount()).getAccount().equals(checkuser);
			boolean b = bcryptPasswordEncoder.matches(checkpassword,
					accountService.getIdentityByAccount(userAccount.getAccount()).getPassword());
			// 使用BCryptPasswordEncoder進行明文密碼與資料庫中加密後的密碼的比對。
			// if else:輸入的帳號密碼相符，導向回首頁，不符導向回登入頁
			if (a.equals(b)) {
				// 將使用者資訊(username、permission、userID)放入userAccount物件以供使用
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
			return "redirect:/register";// 丟出例外時導向回註冊頁
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
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		Integer permission = null;
		// 如果username為空(未登入)，顯示為訪客
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
			permission = userAccount.getPermission();// 查詢userAccount物件中的值:permission，結果為null會丟出例外
		} catch (Exception e) {
			username = "訪客";// 丟出例外時將username設為訪客
			permission = 0;// 丟出例外時將permission設為0(訪客)，用於判斷是否顯示修改/刪除貼文按鈕
		}
		model.addAttribute("user", username);
		// <-- 顯示使用者名稱 End -->
		messageModel.setBoardID(id);// 將自url取得的id放入messageModel實體的BoardID，用model.addAttribute存放至網頁上
		model.addAttribute("messageModel", messageModel);// 存放至網頁上的BoardID為display: none，會在稍後供留言功能使用
		// <-- 顯示貼文內容及留言 Start -->
		MessageboardModel messageboardId = searchMessageboardService.getMessageboardById(id);
		model.addAttribute("id", messageboardId);// 取出messageboard表的資料
		model.addAttribute("userinfo", accountService.getAccountById(messageboardId.getUserID()));// 取出account表的資料
		model.addAttribute("comment", messageService.getComments(id));// 取出message表的資料，塞入Comments實體
		// <-- 顯示貼文內容及留言 End -->
		// <-- 判斷是否顯示修改/刪除貼文按鈕 Start -->
		boolean judgeAuthor = username.equals(accountService.getAccountById(messageboardId.getUserID()).getUsername());// 判斷是否為貼文作者
		boolean judgePermission = permission.equals(2) || permission.equals(3);// 判斷權限是否為管理員
		String crud = "0";// 不是貼文作者也不是管理員，不可修改/刪除貼文
		if (judgeAuthor == true) {// 如果是貼文作者(包含貼文作者是管理員b)，可修改/刪除貼文
			crud = "1";
		} else if (judgePermission == true) {// 如果是管理員，可刪除貼文
			crud = "2";
		}
		model.addAttribute("boardCRUD", crud);
		// <-- 判斷是否顯示修改/刪除貼文按鈕 End -->
		// 判斷是否顯示修改/刪除留言按鈕依靠上方的code(如model.addAttribute("boardCRUD", crud);)
		// + 前端頁面Thymeleaf的th:switch/case、th:if實現，controller無需再撰寫程式碼
		return "postmodel";
	}

	// 在貼文頁中新增留言
	@PostMapping(value = "/postmodel")
	public String postComments(@ModelAttribute MessageModel messageModel, HttpSession session) {
		// <-- 顯示使用者名稱+登入檢查 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
			MessageModel addMessage = new MessageModel();// 如果username不為空(已登入)，新增實體存取資料，用於將留言存至資料庫
			Integer userId = userAccount.getUserID();// 取得userAccount物件中的值:userID
			addMessage.setUserID(userId);// 將值(userId、boardID、memberMessage)存至實體addMessage
			addMessage.setBoardID(messageModel.getBoardID());
			addMessage.setMemberMessage(messageModel.getMemberMessage());
			messageService.saveMessage(addMessage);// 將實體存至資料庫
			Integer boardID = messageModel.getBoardID();
			return "redirect:/postmodel?id=" + boardID;// 重新整理刷新頁面
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
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
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
		try {
			UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");// 取得儲存的Session並強制轉型成UserAccount實體
			Integer UserID = userAccount.getUserID();// 自userAccount實體取得UserID
			messageboardModel.setUserID(UserID);
			searchMessageboardService.saveMessageboard(messageboardModel);
			return "redirect:/";
		} catch (Exception e) {
			return "/401";// 如果報錯，跳轉至Status = 401頁面
		}
		// <-- 新增貼文 End -->
	}

	// 跳轉至註冊頁
	@GetMapping(value = "/register")
	public String registerPage(@Valid Model model) {
		model.addAttribute("accountModel", new AccountModel());// 因為Thymeleaf需要放個AccountModel避免報錯
		return "register";
	}

	// 註冊頁傳值至後端、MySQL儲存
	@PostMapping(value = "/register")
	public String addUser(AccountModel accountModel, Model model) {
		// <-- 新增帳號 Start -->
		try {// 檢查使用者名稱、帳號是否有重複
			Integer checkUsername = accountService.checkExistsUsername(accountModel.getUsername());
			Integer checkAccount = accountService.checkExistsAccount(accountModel.getAccount());
			if (checkUsername == 1 || checkAccount == 1) {// 如果任一重複(此時SQL語法會回傳1)，重新導向會註冊頁(暫時)
				return "redirect:/register";
			} else {// 若兩者都沒有重複(此時SQL語法會回傳0)，則在資料庫中新增該帳號。
				BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();// 新建BCryptPasswordEncoder，用於加密
				String hashPass = bcryptPasswordEncoder.encode(accountModel.getPassword());// 將使用者輸入的密碼自accountModel物件中取出，進行加密
				accountModel.setPassword(hashPass);// 將加密後的密碼放回accountModel物件，取代原本未加密的密碼。
				accountService.addAccount(accountModel);
				return "redirect:/";// 重新導向頁面，使用 redirect 關鍵字，搭配絕對路徑，這邊表示會回到根路徑。
			}
		} catch (Exception e) {
			return "redirect:/401";// 如果有報錯，跳轉至401頁面
		}
		// <-- 新增帳號 End -->
	}

	// 登出功能，清除Session
	@GetMapping(value = "/logout")
	public String logOut(HttpSession session) {
		session.invalidate();// 清除Session
		return "redirect:/";
	}

	// 跳轉至修改貼文頁面
	@GetMapping(value = "/modifyMessageboard")
	public String ToModifyMessageboard(@RequestParam(value = "id", required = true) Integer id, Model model,
			HttpSession session, @ModelAttribute MessageModel messageModel) {
		// <-- 顯示使用者名稱 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		// 如果username為空(未登入)，重新導向回首頁
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
		} catch (Exception e) {
			return "redirect:/";// 丟出例外時重新導向回首頁
		}
		model.addAttribute("user", username);
		// <-- 顯示使用者名稱 End -->
		messageModel.setBoardID(id);// 將自url取得的id放入messageModel實體的BoardID，用model.addAttribute存放至網頁上
		model.addAttribute("messageModel", messageModel);// 存放至網頁上的BoardID為display: none，會在稍後供修改貼文功能使用
		// <-- 將貼文內容顯示在輸入框以供修改 Start -->
		MessageboardModel messageboardModel = searchMessageboardService.getMessageboardById(id);// 取出messageboard表的資料
		model.addAttribute("messageboardModel", messageboardModel);// 將從messageboard表取出，除了CreateTime之外的所有資料，渲染至html上儲存
		return "postmodify";
		// <-- 將貼文內容顯示在輸入框以供修改 End -->
	}

	// 修改貼文頁傳值至後端、MySQL儲存
	@PostMapping(value = "/modifyMessageboard") // 用@ModelAttribute將渲染至html上儲存的資料全部除存到messageboardModel物件中
	public String modifyMessageboard(@ModelAttribute MessageboardModel messageboardModel, MessageModel messageModel,
			HttpSession session) {
		try {
			Date date = new Date();// 取得現在(修改貼文時)時間
			messageboardModel.setCreateTime(date);// 將現在(修改貼文時)時間儲存至messageboardModel物件中
			searchMessageboardService.saveMessageboard(messageboardModel);// 將messageboardModel物件儲存至資料庫
			// 須注意messageboardModel物件所有欄位都必須要有資料
			// 新增與更新操作都是使用save() 方法進行，JPA 會透過主鍵去查詢是否存在，不存在就INSERT，存在就是UPDATE，其中，JPA
			// 只能判斷INSERT 或是UPDATE，並不能判斷出是否更新部分欄位，所以沒有被賦值的欄位都會被覆蓋為NULL，可能導致報錯。
			return "redirect:/";
		} catch (Exception e) {// 報錯時自動導向到401
			return "redirect:/401";
		}

	}

	// 刪除貼文功能
	@GetMapping(value = "/deleteMessageboard")
	public String deleteMessageboard(@RequestParam(value = "id") Integer id, @ModelAttribute MessageModel messageModel,
			HttpSession session) {
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		Integer sessionUserId;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		Integer boardUserId;
		Integer sessionPermission;
		// 如果sessionUserId為空(未登入)，不允許刪除留言
		try {
			boardUserId = searchMessageboardService.getMessageboardById(id).getUserID();// 查詢資料庫取得要刪除貼文的userId
			sessionUserId = userAccount.getUserID();// 查詢userAccount物件中的值:userID，結果為null會丟出例外
			sessionPermission = userAccount.getPermission();// 查詢userAccount物件中的值:Permission，結果為null會丟出例外
			if (boardUserId.equals(sessionUserId) || sessionPermission.equals(2) || sessionPermission.equals(3)) {
				// 如果Session中的userId與userId一致(是貼文作者)或Session中的Permission是2或3(是管理員) // 刪除貼文
				searchMessageboardService.deleteMessageboardById(id);// 允許刪除貼文
				messageService.deleteMessageByBoardID(id);// 刪除貼文時一併刪除該貼文底下的留言
			} else {
				Integer a = 1 / 0;// 否則會丟出例外跳轉至500頁
				// Thymeleaf回傳的/deleteMessage url會帶兩個參數:id與boardID，網址參數是由成對的鍵和值所組成，
				// 以等號 (=) 分隔，網址中的第一個參數固定放在問號後，兩個參數中間用"&"連接，id用於刪除留言，boardID用於刷新原本的貼文頁面
			}
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/500";
		}
	}

	// 跳轉至修改留言頁面
	@GetMapping(value = "/modifyMessage")
	public String toModifyMessage(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "boardID") Integer boardID, Model model, HttpSession session) {// 與跳轉至貼文頁不同，此處的id是指留言的id，boardID是指貼文的id
		// <-- 跳轉至貼文頁照搬過來 Start -->
		// <-- 顯示使用者名稱 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		Integer permission = null;
		// 如果username為空(未登入)，顯示為訪客
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
			permission = userAccount.getPermission();// 查詢userAccount物件中的值:permission，結果為null會丟出例外
		} catch (Exception e) {
			username = "訪客";// 丟出例外時將username設為訪客
			permission = 0;// 丟出例外時將permission設為0(訪客)，用於判斷是否顯示修改/刪除貼文按鈕
		}
		model.addAttribute("user", username);
		// <-- 顯示使用者名稱 End -->
		// <-- 顯示貼文內容及留言 Start -->
		MessageboardModel messageboardId = searchMessageboardService.getMessageboardById(boardID);
		model.addAttribute("id", messageboardId);// 取出messageboard表的資料
		model.addAttribute("userinfo", accountService.getAccountById(messageboardId.getUserID()));// 取出account表的資料
		model.addAttribute("comment", messageService.getComments(boardID));// 取出message表的資料，塞入Comments實體
		// <-- 顯示貼文內容及留言 End -->
		// <-- 判斷是否顯示修改/刪除貼文按鈕 Start -->
		boolean judgeAuthor = username.equals(accountService.getAccountById(messageboardId.getUserID()).getUsername());// 判斷是否為貼文作者
		boolean judgePermission = permission.equals(2) || permission.equals(3);// 判斷權限是否為管理員
		String crud = "0";// 不是貼文作者也不是管理員，不可修改/刪除貼文
		if (judgeAuthor == true) {// 如果是貼文作者(包含貼文作者是管理員b)，可修改/刪除貼文
			crud = "1";
		} else if (judgePermission == true) {// 如果是管理員，可刪除貼文
			crud = "2";
		}
		model.addAttribute("boardCRUD", crud);
		// <-- 判斷是否顯示修改/刪除貼文按鈕 End -->
		// 判斷是否顯示修改/刪除留言按鈕依靠上方的code(如model.addAttribute("boardCRUD", crud);)
		// + 前端頁面Thymeleaf的th:switch/case、th:if實現，controller無需再撰寫程式碼
		// <-- 跳轉至貼文頁照搬過來 End -->
		MessageModel messageModel = messageService.findMessage(id);// 透過url上的id(留言的id)取得要修改的留言
		model.addAttribute("messageModel", messageModel);// 將要修改的留言的欄位(除了CreateTime以外)全部渲染至網頁上儲存
		return "modifymessage";
	}

	// 修改留言功能
	@PostMapping(value = "/modifyMessage")
	public String modifyMessage(@RequestParam(value = "id") Integer id, @ModelAttribute MessageModel messageModel,
			HttpSession session) {
		// <-- 顯示使用者名稱+登入檢查 Start -->
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		String username = null;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		try {
			username = userAccount.getUsername();// 查詢userAccount物件中的值:username，結果為null會丟出例外
			MessageModel addMessage = new MessageModel();// 如果username不為空(已登入)，新增實體存取資料，用於將留言存至資料庫
			Integer userId = userAccount.getUserID();// 取得userAccount物件中的值:userID
			Date date = new Date();// 取得現在(修改留言時)時間
			addMessage.setId(messageModel.getId());// 將值(id、userId、boardID、memberMessage、CreateTime)存至實體addMessage
			addMessage.setUserID(userId);
			addMessage.setBoardID(messageModel.getBoardID());
			addMessage.setMemberMessage(messageModel.getMemberMessage());
			addMessage.setCreateTime(date);// 將現在(修改留言時)時間儲存至messageboardModel物件中
			messageService.saveMessage(addMessage);// 將實體存至資料庫
			Integer boardID = messageModel.getBoardID();
			return "redirect:/postmodel?id=" + boardID;// 跳轉回真正的貼文頁
		} catch (Exception e) {
			return "redirect:/login";// 丟出例外時(未登入)將重新導向至登入頁
		}
		// <-- 顯示使用者名稱+登入檢查 End -->
	}

	// 刪除留言功能
	@GetMapping(value = "/deleteMessage")
	public String deleteMessage(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "boardID") Integer boardID, @ModelAttribute MessageModel messageModel,
			HttpSession session) {
		UserAccount userAccount = (UserAccount) session.getAttribute("getUserAccount");
		Integer sessionUserId;// 自Session中取出userAccount物件(Session中取出的原物件為Object，將其強制轉型成物件UserAccount)
		Integer messageUserId;
		Integer sessionPermission;
		// 如果sessionUserId為空(未登入)，不允許刪除留言
		try {
			messageUserId = messageService.findMessage(id).getUserID();// 查詢資料庫取得要刪除貼文的userId
			sessionUserId = userAccount.getUserID();// 查詢userAccount物件中的值:userID，結果為null會丟出例外
			sessionPermission = userAccount.getPermission();// 查詢userAccount物件中的值:Permission，結果為null會丟出例外
			if (messageUserId.equals(sessionUserId) || sessionPermission.equals(2) || sessionPermission.equals(3)) {
				// 如果Session中的userId與userId一致(是留言作者)或Session中的Permission是2或3(是管理員) // 刪除留言
				messageService.deleteMessage(id);// 允許刪除留言
			} else {
				Integer a = 1 / 0;// 否則會丟出例外跳轉至500頁
				// Thymeleaf回傳的/deleteMessage url會帶兩個參數:id與boardID，網址參數是由成對的鍵和值所組成，
				// 以等號 (=) 分隔，網址中的第一個參數固定放在問號後，兩個參數中間用"&"連接，id用於刪除留言，boardID用於刷新原本的貼文頁面
			}
			return "redirect:/postmodel?id=" + boardID;
		} catch (Exception e) {
			return "redirect:/500";
		}
	}

	// 跳轉至關於留言板
	@GetMapping(value = "/about")
	public String ToAbout() {
		return "about";
	}

	// 跳轉至Status = 401頁面
	@GetMapping(value = "/401")
	public String To401() {
		return "401";
	}

	// 跳轉至Status = 404頁面
	@GetMapping(value = "/404")
	public String To404() {
		return "404";
	}

	// 跳轉至Status = 500頁面
	@GetMapping(value = "/500")
	public String To500() {
		return "500";
	}

	// 測試
	@PostMapping(value = "/test")
	@ResponseBody
	public String test() {
		return "www";
	}
}