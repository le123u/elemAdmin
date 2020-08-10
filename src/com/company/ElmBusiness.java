package com.company;

import com.company.dao.Impl.BusinessDaoImpl;
import com.company.domain.Admin;
import com.company.domain.Business;
import com.company.view.AdminView;
import com.company.view.BusinessView;
import com.company.view.Impl.AdminViewImpl;
import com.company.view.Impl.BusinessViewImpl;
import com.company.view.Impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusiness {
    public static void main(String[] args) {
        work();

    }
    public static  void  work() {
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("-----------------------------------------------------------");

        // 调用商家登录方法
        BusinessDaoImpl dao = new BusinessDaoImpl();
        BusinessView businessView = new BusinessViewImpl();
        Business business = businessView.login();
        if (business != null) {
            int menu = 0;
            System.out.println("~欢迎来到饿了么商家管理系统~");
            while (menu != 5) {
                // 创建一个菜单
                System.out.println("========= 一级菜单（商家管理）1.查看商家信息=2.修改商家信息=3.更新密码=4.所属商品管理=5.退出系统 =========");
                System.out.println("请选择相应的菜单编号");
                menu = input.nextInt();
                switch (menu) {
                    case 1:
                        businessView.showBusinessInfo(business.getBusinessId());
                        break;
                    case 2:
                        businessView.updateBusinessInfo(business.getBusinessId());
                        break;
                    case 3:
                       businessView.updatePassword(business.getBusinessId());
                        break;
                    case 4:
                        FoodViewImpl foodView = new FoodViewImpl();
                        int menuA = 0;
//                        System.out.println("欢迎来到饿了么商家管理系统");
                        while(menuA != 5){
                            System.out.println("========= 二级标题1.所有食物列表=2.搜索食物=3.新建食物=4.删除食物=5.返回一级菜单 =========");
                            System.out.println("请选择相应的编号");
                            menuA = input.nextInt();

                            switch (menuA){
                                case 1:
                                    foodView.listFoodAll(business.getBusinessId());
                                    break;
                                case 2:
                                    foodView.listFoodSave(business.getBusinessId());
                                    break;
                                case 3:
                                    foodView.saveFoodAll(business.getBusinessId());
                                    break;
                                case 4:
                                    foodView.deleteFoodAll(business.getBusinessId());
                                    break;
                                case 5:
                                    System.out.println("========= 欢迎下次光临饿了么系统 =========");
                                    break;
                                default:
                                    System.out.println("没有这个食物");
                                    break;
                            }
                        }

                        break;
                    case 5:
                        System.out.println("========= 欢迎下次光临饿了么系统 =========");
                        break;
                    default:
                        System.out.println("没有这个菜单项");
                        break;
                }
            }
        } else {
            System.out.println("账号或密码有误请重新输入");
        }
    }
}



