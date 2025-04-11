package com.example.controller;

import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 添加商品到購物車
    @PostMapping("/add")
    public Cart addToCart(@RequestParam String cartId, @RequestParam String productId, @RequestParam int quantity) {
        return cartService.addToCart(cartId, productId, quantity);
    }

    // 更新購物車商品數量
    @PutMapping("/update")
    public Cart updateCartItemQuantity(@RequestParam String cartId, @RequestParam String productId, @RequestParam int quantity) {
        return cartService.updateCartItemQuantity(cartId, productId, quantity);
    }

    // 刪除購物車中的商品
    @DeleteMapping("/remove")
    public Cart removeCartItem(@RequestParam String cartId, @RequestParam String productId) {
        return cartService.removeCartItem(cartId, productId);
    }

    // 獲取購物車總價
    @GetMapping("/total")
    public double getCartTotalPrice(@RequestParam String cartId) {
        return cartService.getCartTotalPrice(cartId);
    }

    // 獲取購物車所有商品
    @GetMapping("/items")
    public List<CartItem> getCartItems(@RequestParam String cartId) {
        return cartService.getCartItems(cartId);
    }
}
