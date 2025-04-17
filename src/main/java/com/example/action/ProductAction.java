package com.example.action;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class ProductAction extends ActionSupport {

    private static final int PAGE_SIZE = 12;

    private int currentPage = 1;
    private List<Product> products;
    private int totalPages;

    @Autowired
    private ProductService productService;

    public String list() {
        products = productService.getProducts(currentPage, PAGE_SIZE);
        totalPages = productService.getTotalPages(PAGE_SIZE);
        return SUCCESS;
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
}
