package com.company.dao;

import com.company.domain.Admin;
import com.company.domain.Business;

import java.util.List;

public interface BusinessDao {

    public List<Business> findAll(String businessName,String businessAddress);

    public int save(String businessName);
//
//    public void update(Business business);
//
   public Integer delete(Integer businessId);

    public Business getBusinessByNameByPass(Integer businessId, String password);

    public Business getBusinessByBusinessId(Integer businessId);

    public int updateBusiness(Business business);

    public int updatePassword(Business business);


}
