package com.company.dao;

import com.company.domain.Business;

import java.util.List;

public interface BusinessDao {

    public List<Business> findAll(String businessName,String businessAddress);

    public int save(String businessName);
//
//    public void update(Business business);
//
   public Integer delete(Integer businessId);

}
