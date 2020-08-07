package com.company.dao.Impl;

import com.company.dao.BusinessDao;
import com.company.domain.Business;
import com.company.domain.Food;
import com.company.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Business> findAll(String businessName, String businessAddress) {
        ArrayList<Business> list = null;

        StringBuffer sql = new StringBuffer("select * from business where 1 = 1");
        if (businessName != null && !businessName.equals("")) {
            // 传入商家名
            sql.append(" and businessName like '%").append(businessName).append("%' ");
            System.out.println(sql);
        }
        if (businessAddress != null && !businessAddress.equals("")) {
            // 传入商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
            System.out.println(sql);
        }

     try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
           list = new ArrayList<>();
            rs = pstmt.executeQuery();
            while (rs.next()){
                Business business = new Business();

                list.add(new Business(
                        rs.getInt("businessId")
                        ,rs.getString("password")
                        ,rs.getString("businessName")
                        ,rs.getString("businessAddress")
                        ,rs.getString("businessExplain")
                        ,rs.getBigDecimal("starPrice")
                        ,rs.getBigDecimal("deliveryPrice")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return list;
    }

    @Override
    public int save(String businessName) {
        int businessId = 0;
        // 附带一个初始密码
        String sql = "insert into business(businessName, password)values(?, '123456')";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 可以在prepareStatement中设置返回自增长列的值
            pstmt.setString(1, businessName);
            pstmt.executeUpdate();
            // 获取自增长的列
            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                businessId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return businessId;

    }


//
//    @Override
//    public void save(Business business) {
//        try {
//            conn = JDBCUtils.getConnection();
//            String sql = "insert into  business values (null,?,?,?,?,?,?)";
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setString(1,business.getPassword());
//            pstmt.setString(2,business.getBusinessName());
//            pstmt.setString(3,business.getBusinessAddress());
//            pstmt.setString(4,business.getBusinessExplain());
//            pstmt.setBigDecimal(5,business.getStarPrice());
//            pstmt.setBigDecimal(6,business.getDeliveryPrice());
//
//            int count = pstmt.executeUpdate();
//            if (count > 0){
//                System.out.println("添加成功");
//            }else{
//                System.out.println("添加失败");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.close(pstmt,conn);
//
//        }
//
//    }

//    @Override
//    public void update(Business business) {
//        try {
//            conn = JDBCUtils.getConnection();
//            String sql = "update business set deliveryPrice = ? where businessId = ?";
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setBigDecimal(1,business.getDeliveryPrice());
//            pstmt.setInt(2,business.getBusinessId());
//
//
//            int count = pstmt.executeUpdate();
//            if (count > 0){
//                System.out.println("修改成功");
//            }else{
//                System.out.println("修改失败");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.close(pstmt,conn);
//
//        }
//
//
//
//    }
//
    @Override
    public Integer delete(Integer businessId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from business where businessId = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,businessId);

            int count = pstmt.executeUpdate();

            if (count > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);

        }

    return businessId;
    }

}

