package com.example.action;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("productAction")
public class ProductAction extends ActionSupport {
	private static final Logger logger = LoggerFactory.getLogger(ProductAction.class);
    private static final int PAGE_SIZE = 9;
    private int currentPage = 1;
    private List<Product> products;
    private int totalPages;
    private Product product;
    private int productId;

    @Autowired
    private ProductService productService;

    public String list() {
        products = productService.getProducts(currentPage, PAGE_SIZE);
        totalPages = productService.getTotalPages(PAGE_SIZE);
        return SUCCESS;
    }

    public String detail() {
        try {
            // 驗證參數
            if (productId <= 0) {
                logger.warn("無效的商品ID: {}", productId);
                addActionError("無效的商品ID");
                return ERROR;
            }
            
            // 查詢商品
            logger.debug("查詢商品ID: {}", productId);
            product = productService.getProductById(productId);
            
            // 檢查結果
            if (product == null) {
                logger.warn("找不到商品ID: {}", productId);
                addActionError("找不到該商品");
                return ERROR;
            }
            
            logger.debug("成功查詢商品: {}", product.getName());
            return SUCCESS;
        } catch (Exception e) {
            logger.error("查詢商品失敗, ID: " + productId, e);
            addActionError("系統錯誤，請稍後再試");
            return ERROR;
        }
    }
    

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
}