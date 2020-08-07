package com.company.Test;

import com.company.dao.AdminDao;
import com.company.dao.Impl.AdminDaoImpl;
import com.company.domain.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDaoImpl();
        Admin admin = adminDao.getAdminByNameByPass("王磊", "123");
        System.out.println(admin);

    }
}
