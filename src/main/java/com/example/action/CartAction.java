package com.example.action;

import com.example.pojo.entity.Cart;
import com.example.service.CartService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller("cartAction")
public class CartAction extends ActionSupport {
    
    @Autowired
    private CartService cartService;
    
    private String productId;
    private int quantity;
    private Cart cart;
    
    public String viewCart() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String userId = (String) request.getSession().getAttribute("userId");
            if (userId == null) {
                return LOGIN;
            }
            cart = cartService.getCartByUserId(userId);
         // 更新購物車數量顯示
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
                return LOGIN;
            }
            
            double price = Double.parseDouble(request.getParameter("price"));
            cartService.addToCart(userId, productId, quantity, price);
            
            // 更新購物車數量
            int cartItemCount = cartService.getCartItemCount(userId);
            request.getSession().setAttribute("cartItemCount", cartItemCount);
            
            return SUCCESS;
        } catch (NumberFormatException e) {
            addActionError("價格格式錯誤");
            return ERROR;
        } catch (Exception e) {
            addActionError("加入購物車失敗: " + e.getMessage());
            return ERROR;
        }
    }
    
    public String updateCart() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return LOGIN;
        }
        
        cartService.updateCartItem(userId, productId, quantity);
        
        // 更新購物車數量
        int cartItemCount = cartService.getCartItemCount(userId);
        request.getSession().setAttribute("cartItemCount", cartItemCount);
        
        return SUCCESS;
    }
    
    public String removeFromCart() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return LOGIN;
        }
        
        cartService.removeFromCart(userId, productId);
        
        // 更新購物車數量
        int cartItemCount = cartService.getCartItemCount(userId);
        request.getSession().setAttribute("cartItemCount", cartItemCount);
        
        return SUCCESS;
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