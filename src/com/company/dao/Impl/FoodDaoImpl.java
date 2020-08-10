package com.company.dao.Impl;

import com.company.dao.FoodDao;
import com.company.domain.Admin;
import com.company.domain.Food;
import com.company.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection conn =null;
    private PreparedStatement pstmt =null;
    private ResultSet rs =null;
    @Override
    public List<Food> findAll(Integer businessId) {
        String sql ="select * from food where businessId = ?";
        ArrayList<Food> foods = null;
        try{

            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            foods = new ArrayList<>();
            rs = pstmt.executeQuery();
            while (rs.next()){
                foods.add(new Food(
                        rs.getInt("foodId")
                        ,rs.getString("foodName")
                        ,rs.getString("foodExplain")
                        ,rs.getBigDecimal("foodPrice")
                        ,rs.getInt("businessId")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return foods;


    }

    @Override
    public Integer save(String foodName) {
        int foodId = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into  food (foodId,foodName,foodExplain,foodPrice,businessId)values (10,?,'食品',12,10003)";
            pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,foodName);
//            pstmt.setString(2,food.getFoodName());
//            pstmt.setString(3,food.getFoodExplain());
//            pstmt.setBigDecimal(4,food.getFoodPrice());
//            pstmt.setInt(5,food.getBusinessId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                foodId = rs.getInt(1);
            }

//            int count = pstmt.executeUpdate();
//            if (count > 0){
//                System.out.println("添加成功");
//            }else{
//                System.out.println("添加失败");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);

        }
        return foodId;
    }

//    @Override
//    public void update(Food food) {
//        try {
//            conn = JDBCUtils.getConnection();
//            String sql = "update food set foodPrice = ? where foodId = ?";
//            pstmt = conn.prepareStatement(sql);
//
//            pstmt.setBigDecimal(1,food.getFoodPrice());
//            pstmt.setInt(2,food.getFoodId());
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
//    }

    @Override
    public Integer delete(Integer foodId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from food where foodId = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,foodId);

//            int count = pstmt.executeUpdate();
//
//            if (count > 0){
//                System.out.println("删除成功");
//            }else{
//                System.out.println("删除失败");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);

        }

        return foodId;
    }
}
