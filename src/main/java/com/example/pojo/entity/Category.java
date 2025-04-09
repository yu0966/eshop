package com.example.pojo.entity;

import java.util.Date;
import java.util.List;

/**
 * 商品分類實體類別，用來封裝商品分類資料。
 */
public class Category {

    // 分類的唯一識別碼
    private int id;

    // 分類名稱
    private String name;

    // 描述分類的詳細資訊
    private String description;
    
    private Date createdAt;
    
    private Date updatedAt;

    // 商品分類下的商品列表
    private List<Product> products;

    // 取得分類的 ID
    public int getId() {
        return id;
    }

    // 設定分類的 ID
    public void setId(int id) {
        this.id = id;
    }

    // 取得分類的名稱
    public String getName() {
        return name;
    }

    // 設定分類的名稱
    public void setName(String name) {
        this.name = name;
    }

    // 取得分類的描述
    public String getDescription() {
        return description;
    }

    // 設定分類的描述
    public void setDescription(String description) {
        this.description = description;
    }

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

 
    // 取得分類下的商品列表
    public List<Product> getProducts() {
        return products;
    }

    // 設定分類下的商品列表
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
