package com.company;

import com.company.dao.Impl.FoodDaoImpl;
import com.company.domain.Admin;
import com.company.view.Impl.AdminViewImpl;
import com.company.view.Impl.FoodViewImpl;

import java.util.Scanner;

public class ElmAdmin02 {
    public static void main(String[] args) {
        work();
    }
    public static void work(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("-----------------------------------------------------------");

        // 调用登录方法
        AdminViewImpl adminView = new AdminViewImpl();
        Admin admin = adminView.login();

        FoodViewImpl foodView = new FoodViewImpl();
        FoodDaoImpl foodDao = new FoodDaoImpl();

        if (admin != null){
            int menu = 0;
            System.out.println("欢迎来到饿了么商家管理系统");
            while(menu != 5){
                System.out.println("========= 1.所有食物列表=2.搜索食物=3.新建食物=4.删除食物=5.退出系统 =========");
                System.out.println("请选择相应的食物编号");
                menu = input.nextInt();

                switch (menu){
                    case 1:
                        foodView.listFoodAll();
                        break;
                    case 2:
                        foodView.listFoodSave();
                        break;
                    case 3:
                        foodView.saveFoodAll();
                        break;
                    case 4:
                        foodView.deleteFoodAll();
                        break;
                    case 5:
                        System.out.println("========= 欢迎下次光临饿了么系统 =========");
                        break;
                    default:
                        System.out.println("没有这个食物");
                        break;
                }
            }
        }else{
            System.out.println("请输入正确的用户名和密码");
        }
    }
}
