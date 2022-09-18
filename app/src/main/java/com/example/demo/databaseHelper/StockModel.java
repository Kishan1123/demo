package com.example.demo.databaseHelper;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "stock_table")
public class StockModel implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String category;
    public String price;
    public boolean inStock;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

//    public StockModel(String image, String title, String category, String price, boolean inStock) {
//      this.image=image;
//      this.title=title;
//      this.category=category;
//      this.price = price;
//      this.inStock = inStock;
//    }

    public StockModel() {

    }

    public StockModel(byte[] image,String title, String spinner, String price, boolean stockIn) {
        this.image = image;
        this.title=title;
        this.category=spinner;
        this.price = price;
        this.inStock = stockIn;
    }

    public StockModel( String title, String spinner, String price, boolean stockIn) {
        this.image = image;
        this.title = title;
        this.category = spinner;
        this.price = price;
        this.inStock = stockIn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
