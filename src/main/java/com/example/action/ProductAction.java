package com.example.action;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import com.example.service.ProductService;
import com.example.service.CartService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("productAction")
public class ProductAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProductAction.class);
	private static final int PAGE_SIZE = 9;
	private int currentPage = 1;
	private List<Product> products;
	private int totalPages;
	private Product product;
	private int productId;
	private Integer categoryId;
	private List<Category> categories;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	public String list() {
		categories = productService.getAllCategories();

		if (categoryId != null && categoryId > 0) {
			products = productService.getProductsByCategory(categoryId, currentPage, PAGE_SIZE);
			totalPages = productService.getTotalPagesByCategory(categoryId, PAGE_SIZE);
		} else {
			products = productService.getProducts(currentPage, PAGE_SIZE);
			totalPages = productService.getTotalPages(PAGE_SIZE);
		}

		// 獲取購物車數量
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			int cartItemCount = cartService.getCartItemCount(userId);
			session.setAttribute("cartItemCount", cartItemCount);
		}

		return SUCCESS;
	}

//這個方法會返回一個 String 類型的值，通常在這裡用來返回一個狀態（例如 "success" 或 "error"
	public String detail() {
		// 是用來包裹一段可能會拋出異常的代碼，這樣如果出現錯誤，Java 可以捕捉這些錯誤並進行處理。這樣可以避免程序崩潰
		try {
			if (productId <= 0) {
				// 日誌記錄，它會在控制台或日誌文件中記錄警告訊息，表示商品 ID 無效。
				logger.warn("無效的商品ID: {}", productId);
				// 這行代碼會把錯誤訊息添加到 Action 的錯誤列表中，這樣在界面上就可以顯示「無效的商品ID」這個錯誤信息
				addActionError("無效的商品ID");
				return ERROR;
			}
			/*
			 * 可以在控制台或日誌中看到正在查詢哪個商品的 ID logger.debug
			 * 是一個比較低級別的日誌，它通常用來記錄一些開發和調試過程中的詳細信息，正常使用者是看不到
			 */
			logger.debug("查詢商品ID: {}", productId);
			/*
			 * 這行代碼會調用 productService 來根據 productId 查詢商品。
			 * productService.getProductById(productId) 是用來從數據庫或其他地方獲取商品資訊的方法。 product
			 * 變數會被賦值為查詢到的商品對象，如果找不到商品，這個變數會是 null
			 */
			product = productService.getProductById(productId);

			if (product == null) {
				// 這會在日誌中記錄警告，表示找不到該商品
				logger.warn("找不到商品ID: {}", productId);
				// 這會將錯誤訊息「找不到該商品」加入到錯誤列表，讓前端界面顯示該錯誤
				addActionError("找不到該商品");
				return ERROR;
			}

			// 獲取當前的 HTTP Session。HttpSession 用來存儲用戶的會話信息，這樣在多次請求之間可以保持用戶的狀態。
			HttpSession session = ServletActionContext.getRequest().getSession();
			// 從會話中獲取 userId。getAttribute("userId") 會返回用戶的 ID，這是之前存儲在 Session 中的。
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				// 調用 cartService 來查詢該用戶購物車中的商品數量
				int cartItemCount = cartService.getCartItemCount(userId);
				// 把查詢到的購物車商品數量（cartItemCount）存儲到 session 中，這樣在其他地方可以方便地訪問
				session.setAttribute("cartItemCount", cartItemCount);
			}
			// 這條日誌記錄表示查詢商品成功，並顯示商品的名稱。這對開發人員來說是一個有用的調試信息
			logger.debug("成功查詢商品: {}", product.getName());
			return SUCCESS;
			// 如果在 try 代碼塊中發生任何異常，會進入 catch 區塊，捕獲錯誤
		} catch (Exception e) {
			// 這會記錄錯誤的詳細信息，包括錯誤的商品 ID 和異常信息
			logger.error("查詢商品失敗, ID: " + productId, e);
			// 將錯誤信息添加到錯誤列表，讓用戶界面顯示「系統錯誤，請稍後再試」的提示
			addActionError("系統錯誤，請稍後再試");
			return ERROR;
		}
	}

	// Getters and Setters
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Product> getProducts() {
		return products;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public Product getProduct() {
		return product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<Category> getCategories() {
		return categories;
	}
}