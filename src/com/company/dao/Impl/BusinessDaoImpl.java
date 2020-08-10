package com.company.dao.Impl;

import com.company.dao.BusinessDao;
import com.company.domain.Admin;
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
            while (rs.next()) {
                Business business = new Business();

                list.add(new Business(
                        rs.getInt("businessId")
                        , rs.getString("password")
                        , rs.getString("businessName")
                        , rs.getString("businessAddress")
                        , rs.getString("businessExplain")
                        , rs.getBigDecimal("starPrice")
                        , rs.getBigDecimal("deliveryPrice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
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
            if (rs.next()) {
                businessId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
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
        // 删除的时候注意需要开启事物
        int result = 0;
        String sql = "delete from business where businessId = ?";

        try {
            conn = JDBCUtils.getConnection();
            // 手动开启事物
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            result = pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            // 进入了异常的代码区要给result置为 0
            result = 0;
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();


        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return result;

    }

    @Override
    public Business getBusinessByNameByPass(Integer businessId, String password) {
        Business business = null;
        String sql = "select * from business where businessId = ? and password = ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getBigDecimal("starPrice"));
                business.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return business;


    }

    @Override
    public Business getBusinessByBusinessId(Integer businessId) {
        Business business = null;
        String sql = "select * from business where businessId = ? ";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getBigDecimal("starPrice"));
                business.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return business;


    }

    @Override
    public int updateBusiness(Business business) {
        int result = 0;

        try{

        String sql = "update business set businessName = ? , businessAddress = ? ," +
                "businessExplain = ? , starPrice = ? , deliveryPrice = ? where" +
                " businessId = ?";

        conn = JDBCUtils.getConnection();
        pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
        pstmt.setString(1, business.getBusinessName());
        pstmt.setString(2, business.getBusinessAddress());
        pstmt.setString(3, business.getBusinessExplain());
        pstmt.setBigDecimal(4, business.getStarPrice());
        pstmt.setBigDecimal(5, business.getDeliveryPrice());
        pstmt.setInt(6, business.getBusinessId());


        result = pstmt.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }finally{
        JDBCUtils.close(rs, pstmt, conn);
    }
        return result;
}

    @Override
    public int updatePassword(Business business) {
        int result = 0;
        try{

            String sql = "update business set password = ?  where  businessId = ?";

            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            pstmt.setString(1, business.getPassword());
            pstmt.setInt(2, business.getBusinessId());

            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            JDBCUtils.close(rs, pstmt, conn);
        }
        return result;
    }


}




