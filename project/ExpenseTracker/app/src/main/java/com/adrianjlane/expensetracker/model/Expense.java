package com.adrianjlane.expensetracker.model;

import java.util.Date;

public class Expense {
    private Long id;
    private Date date;
    private double amount;
    private long categoryId;
    private String merchant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", categoryId=" + categoryId +
                ", merchant='" + merchant + '\'' +
                '}';
    }
}
