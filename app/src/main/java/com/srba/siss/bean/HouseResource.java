package com.srba.siss.bean;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/5
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/5         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HouseResource {

    /**
     * id : 1
     * asId : 1
     * houseCardNo : 2
     * houseInfoNo : 3
     * neighbourhood : 小区
     * region : 区域
     * regionDetail : 片区
     * building : 楼盘
     * buildingNo : 8
     * floor : 3
     * houseType : 户型
     * area : 面积
     * price : 0
     * averagePrice : 0
     * decoration : 装修程度
     * direction : 朝向
     */

    private int id;
    private int asId;
    private String houseCardNo;
    private String houseInfoNo;
    private String neighbourhood;
    private String region;
    private String regionDetail;
    private String building;
    private int buildingNo;
    private int floor;
    private String houseType;
    private String area;
    private double price;
    private double averagePrice;
    private String decoration;
    private String direction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public String getHouseCardNo() {
        return houseCardNo;
    }

    public void setHouseCardNo(String houseCardNo) {
        this.houseCardNo = houseCardNo;
    }

    public String getHouseInfoNo() {
        return houseInfoNo;
    }

    public void setHouseInfoNo(String houseInfoNo) {
        this.houseInfoNo = houseInfoNo;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionDetail() {
        return regionDetail;
    }

    public void setRegionDetail(String regionDetail) {
        this.regionDetail = regionDetail;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
