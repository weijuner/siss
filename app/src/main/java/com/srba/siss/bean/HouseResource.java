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
     * houseInfoNo : 3
     * contractNo : null
     * neighbourhood : 小区
     * region : 区域
     * regionDetail : 片区
     * floor : 3
     * room : null
     * hall : null
     * year : null
     * area : 面积
     * price : 0
     * averagePrice : 0
     * decoration : 装修程度
     * direction : 朝向
     */

    private int id;
    private int asId;
    private String houseInfoNo;
    private String contractNo;
    private String neighbourhood;
    private String region;
    private String regionDetail;
    private int floor;
    private int room;
    private int hall;
    private int year;
    private double area;
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

    public String getHouseInfoNo() {
        return houseInfoNo;
    }

    public void setHouseInfoNo(String houseInfoNo) {
        this.houseInfoNo = houseInfoNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
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
