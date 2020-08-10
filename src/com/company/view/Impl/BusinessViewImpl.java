package com.company.view.Impl;

import com.company.dao.BusinessDao;
import com.company.dao.Impl.BusinessDaoImpl;
import com.company.domain.Business;
import com.company.view.BusinessView;
import javafx.application.Preloader;

import java.math.BigDecimal;
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

    @Override
    public Business login() {
        System.out.println("请输入商家编号：");
        Integer businessId = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        return dao.getBusinessByNameByPass(businessId,password);
    }

    @Override
    public void showBusinessInfo(Integer businessId) {

        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business businessByBusinessId = dao.getBusinessByBusinessId(businessId);
        System.out.println(businessByBusinessId);

    }

    @Override
    public void updateBusinessInfo(Integer businessId) {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByBusinessId(businessId);
        // 先显示一遍商家信息 方便查看
        String inputStr = "";
        String iputStr2 ="";
        String iputStr3 ="";
        String iputStr4 ="";
        String iputStr5 ="";
        System.out.println(business);
        System.out.println("是否修改商家信息（y/n）");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的商家名称");
            business.setBusinessName(input.next());
        }
        System.out.println("是否修改商家描述（y/n）");
        iputStr3 = input.next();
        if (iputStr3.equals("y")){
            System.out.println("请输入新的商家描述");
            business.setBusinessExplain(input.next());
        }
        System.out.println("是否修改起送价格（y/n）");
        iputStr4 = input.next();
        if (iputStr4.equals("y")){
            System.out.println("请输入新的起送价格");
//            BigDecimal bigDecimal = new BigDecimal();
            business.setStarPrice(input.nextBigDecimal());
        }
        System.out.println("是否修改配送费（y/n）");
        iputStr5 = input.next();
        if (iputStr5.equals("y")){
            System.out.println("请输入新的配送费");
            business.setDeliveryPrice(input.nextBigDecimal());
        }
        int res = dao.updateBusiness(business);
        if(res > 0)
            System.out.println("修改商家信息成功");
        else
            System.out.println("修改商家信息失败");
    }

    @Override
    public void updateBusinessByPassword(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByBusinessId(businessId);

        System.out.println("\n请输入旧密码：");
        String oldPass = input.next();
        System.out.println("\n请输入新密码：");
        String password = input.next();
        System.out.println("\n请再次输入新密码：");
        String beginPassword = input.next();

        if(!business.getPassword().equals(oldPass)) {
            System.out.println("\n旧密码输入错误！");
        }else if(!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致！");
        }else {
            int result = dao.updateBusinessByPassword(businessId, password);
            if(result>0) {
                System.out.println("\n修改密码成功！");
            }else {
                System.out.println("\n修改密码失败！");
            }
        }
    }


}

