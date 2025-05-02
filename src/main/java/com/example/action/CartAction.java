package com.example.action;

import com.example.pojo.entity.Cart;
import com.example.service.CartService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller("cartAction")
@Scope("prototype")  // 添加這行確保每次請求都是新實例
public class CartAction extends ActionSupport {
    
    private static final long serialVersionUID = 1L;

    @Autowired
    private CartService cartService;
    
    private String productId;
    private int quantity;
    private Cart cart;
    private InputStream inputStream;
    
    public String viewCart() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String userId = (String) request.getSession().getAttribute("userId");
            if (userId == null) {
                return LOGIN;
            }
            cart = cartService.getCartByUserId(userId);
            int cartItemCount = cartService.getCartItemCount(userId);
            request.getSession().setAttribute("cartItemCount", cartItemCount);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("獲取購物車失敗: " + e.getMessage());
            return ERROR;
        }
    }
    
    public String addToCart() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String userId = (String) request.getSession().getAttribute("userId");
            if (userId == null) {
                String jsonResponse = "{\"status\":\"error\", \"message\":\"請先登入\"}";
                inputStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
            
            double price = Double.parseDouble(request.getParameter("price"));
            cartService.addToCart(userId, productId, quantity, price);
            
            // 更新購物車數量
            int cartItemCount = cartService.getCartItemCount(userId);
            request.getSession().setAttribute("cartItemCount", cartItemCount);
            
            String jsonResponse = "{\"status\":\"success\", \"cartItemCount\":" + cartItemCount + "}";
            inputStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));
            return SUCCESS;
            
        } catch (NumberFormatException e) {
            String jsonResponse = "{\"status\":\"error\", \"message\":\"價格格式錯誤\"}";
            inputStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        } catch (Exception e) {
            String jsonResponse = "{\"status\":\"error\", \"message\":\"加入購物車失敗: " + e.getMessage() + "\"}";
            inputStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
    }
    
    // 其他方法保持不變...
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
    // Getters and Setters
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Cart getCart() {
        return cart;
    }
}