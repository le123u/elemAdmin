package com.company.view.Impl;

import com.company.dao.BusinessDao;
import com.company.dao.Impl.BusinessDaoImpl;
import com.company.domain.Business;
import com.company.view.BusinessView;
import javafx.application.Preloader;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    @Override
    public void listBusinessAll() {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List<Business> list = dao.findAll(null, null);
        System.out.println("商家编号\t商家密码\t商家名称\t商家地址\t食品种类\t食品价格\t配送费");
        for (Business b :list){
            System.out.println(b.getBusinessId()+ "\t" + b.getPassword()+"\t"+
                    b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+
                    b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());
        }
    }

    private Scanner input = new Scanner(System.in);
    @Override
    public void listBusinessSave() {
        String businessName = "";
        String businessAddress = "";
        String inputStr = "";
        System.out.println("是否需要输入商家名称关键字(y/n): ");
        inputStr = input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家名称关键字：");
            businessName = input.next();
        }
        System.out.println("是否需要输入商家地址关键字(y/n): ");
        inputStr = input.next();
        if(inputStr.equals("y")){
            System.out.println("请输入商家地址关键字：");
            businessAddress = input.next();
        }

        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.findAll(businessName, businessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b :list){
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());
        }

    }

    /**
     * 保存商家
     */
    @Override
    public void saveBusinessAll() {
        System.out.println("请输入商家名字：");
        String businessName = input.next();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        int businessId = dao.save(businessName);
        if (businessId > 0) {
            System.out.println("新建商家成功！ 商家编号为" + businessId);

        } else {
            System.out.println("新建商家失败！");

        }

    }

    @Override
    public void deleteBusinessAll() {
        System.out.println("请输入商家编号：");
        int i = input.nextInt();
        System.out.println("确认要删除吗？（y/n）");
        String next = input.next();

        if (next.equals("y")){
            BusinessDaoImpl dao = new BusinessDaoImpl();
//            dao.delete(i);
            if (dao.delete(i) > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }

        }else{
            System.out.println("取消删除");
        }




    }
}

