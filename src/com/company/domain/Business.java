package com.company.domain;

import java.math.BigDecimal;

public class Business {
    private Integer businessId;
    private String password;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private BigDecimal starPrice;
    private BigDecimal deliveryPrice;

    public Business() {
    }

    public Business(Integer businessId, String password, String businessName, String businessAddress, String businessExplain, BigDecimal starPrice, BigDecimal deliveryPrice) {
        this.businessId = businessId;
        this.password = password;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessExplain = businessExplain;
        this.starPrice = starPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessExplain() {
        return businessExplain;
    }

    public void setBusinessExplain(String businessExplain) {
        this.businessExplain = businessExplain;
    }

    public BigDecimal getStarPrice() {
        return starPrice;
    }

    public void setStarPrice(BigDecimal starPrice) {
        this.starPrice = starPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {
        return "\n商家编号："+this.businessId+
                "\n商家名称："+this.businessName+
                "\n商家地址："+this.businessAddress+
                "\n商家介绍："+this.businessExplain+
                "\n起送费："+this.starPrice+
                "\n配送费："+this.deliveryPrice;
    }
}
