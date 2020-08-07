package com.company.dao;

import com.company.domain.Admin;

import java.util.List;

public interface AdminDao {

    public Admin getAdminByNameByPass(String adminName, String password);

    public List<Admin> findAll();

    public void save(Admin admin);

    public void update(Admin admin);

    public void delete(Integer adminId);

}

