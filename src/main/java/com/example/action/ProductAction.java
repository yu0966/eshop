//🔸 宣告這個類別放在 com.example.action 套件裡，也就是 Struts 的 Action 類別區。
package com.example.action;
/*
 * 匯入你需要用到的類別：
 * Product：商品的資料結構
 * ProductService：提供查詢商品的服務
 * ActionSupport：Struts2 的基本 Action 類別（你要繼承它才有 SUCCESS 等常數）
 * @Autowired 和 @Controller：Spring 的自動注入 + 管理這個類別的標記
 * List：用來接收多筆商品資料
*/
import com.example.pojo.entity.Product;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

//標記這是一個由 Spring 管理的 Controller（其實它是個 Struts 的 Action 類別）
@Controller
public class ProductAction extends ActionSupport {

    private static final int PAGE_SIZE = 10;//每頁要顯示幾筆商品？這裡設定為「一次 10 筆」

    private int currentPage = 1;// 目前是第幾頁？預設從第 1 頁開始
    private List<Product> products;//用來存放這一頁要顯示的商品清單
    private int totalPages;//計算總共有幾頁（要顯示頁碼用）

    @Autowired
    private ProductService productService;//請 Spring 自動幫你注入 ProductService 服務，這樣你可以呼叫它來查資料

    public String list() {
        products = productService.getProducts(currentPage, PAGE_SIZE);//根據目前第幾頁、每頁幾筆 → 撈出那一頁的商品資料
        totalPages = productService.getTotalPages(PAGE_SIZE);//算出總共幾頁（用來畫分頁按鈕）
        return SUCCESS;//最後回傳 SUCCESS，Struts2 就會去找對應的 JSP（例如 list.jsp）
    }
/*
 * Struts2 會幫你：
 * 用 setter 把網址參數塞進來（例如：?currentPage=3 → 自動呼叫 setCurrentPage(3)）
 * 用 getter 把你在 list() 中塞的 products、totalPages 等資料，傳給 JSP 顯示出來
*/
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
}
/*
使用者點「第 2 頁」 →
URL: productList.action?currentPage=2 →
    Struts2 幫你塞值（setCurrentPage(2)） →
        執行 list() 方法 →
            productService 查資料 →
            把 products 和 totalPages 設好 →
        回傳 SUCCESS →
    導向 list.jsp →
        用 getter 拿資料來顯示商品 + 分頁按鈕
*/