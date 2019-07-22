package com.adrianjlane.expensetracker.dto;

public class CategoryDto {

    private Long categoryId;
    private String categoryName;
    private Double categoryBudget;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getCategoryBudget() {
        return categoryBudget;
    }

    public void setCategoryBudget(Double categoryBudget) {
        this.categoryBudget = categoryBudget;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryBudget=" + categoryBudget +
                '}';
    }
}
