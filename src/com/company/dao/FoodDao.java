package com.company.dao;

import com.company.domain.Admin;
import com.company.domain.Food;

import java.util.List;

public interface FoodDao {

    public List<Food> findAll(Integer businessId);

    public Integer save(String foodName);

   // public void update(Food food);

    public Integer delete(Integer foodId);
}
