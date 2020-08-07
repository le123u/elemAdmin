package com.company.Test;

import com.company.dao.AdminDao;
import com.company.dao.Impl.AdminDaoImpl;
import com.company.domain.Admin;

import java.util.List;

public class TestAdmin {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDaoImpl();
        Admin admin = adminDao.getAdminByNameByPass("王磊", "123");
        System.out.println(admin);

//        List<Admin> list = adminDao.findAll();
//        for (Admin list1:list){
//            System.out.println(list1);
//        }

//        Admin admin1 = new Admin(3, "李四", "345");
//        adminDao.save(admin1);

//        Admin admin2 = new Admin(11, null, "555");
//        adminDao.update(admin2);

        adminDao.delete(11);

    }
}
