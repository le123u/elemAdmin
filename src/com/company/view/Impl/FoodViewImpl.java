package com.company.view.Impl;

import com.company.dao.Impl.FoodDaoImpl;
import com.company.domain.Food;
import com.company.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    @Override
    public void listFoodAll(Integer businessId) {
        FoodDaoImpl foodDao = new FoodDaoImpl();
        List<Food> list = foodDao.findAll(businessId);
        System.out.println("食物编号\t食物名称\t食物种类\t食物价格\t商家编号");
        for(Food li:list){
            System.out.println(li.getFoodId()+ "\t" + li.getFoodName()+"\t"+
                    li.getFoodExplain()+"\t"+li.getFoodPrice()+"\t"+
                    li.getBusinessId());
        }
    }

    private Scanner input = new Scanner(System.in);

    @Override
    public void listFoodSave(Integer businessId) {
        String s = "";
        String foodName = "";
        String foodExplain = "";
        System.out.println("是否需要输入食品名称关键字（y/n）");
        s = input.next();
        if (s.equals("y")){
            System.out.println("请输入食品名称关键字：");
            foodName = input.next();
        }
        System.out.println("是否需要输入食品的种类（y/n）");
        String s1 = input.next();
        if (s1.equals("y")){
            System.out.println("请输入食品的种类：");
            foodExplain = input.next();
        }
        FoodDaoImpl foodDao = new FoodDaoImpl();
        List<Food> list1 = foodDao.findAll(businessId);
        System.out.println("食物编号\t食物名称\t食物种类\t食物价格\t商家编号");
        for(Food li:list1){
            System.out.println(li.getFoodId()+ "\t" + li.getFoodName()+"\t"+
                    li.getFoodExplain()+"\t"+li.getFoodPrice()+"\t"+
                    li.getBusinessId());
        }

    }

    @Override
    public void saveFoodAll(Integer businessId) {
        System.out.println("请输入要增加的食物名称：");
        String foodName = input.next();
        FoodDaoImpl foodDao = new FoodDaoImpl();
        Integer foodId = foodDao.save(foodName);
        if (foodId > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }


    }

    @Override
    public void deleteFoodAll(Integer businessId) {
        System.out.println("请输入要删除的食物编号");
        int foodId = input.nextInt();
        System.out.println("确定要删除吗（y/s）");
        String next = input.next();
        if (next.equals("y")){
            FoodDaoImpl foodDao = new FoodDaoImpl();
            foodDao.delete(foodId);
            if (foodDao.delete(foodId) > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        }else{
            System.out.println("取消删除");
        }

    }
}
