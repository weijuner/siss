package com.srba.siss.bean;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/13
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/13         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */

public class BuyerEmand {
    /**
     *
     */
    private Long id;

    /**
     * 买家id
     */
    private Long abId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 手机号码
     */
    private String phoneNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    public Double getMinArea() {
        return minArea;
    }

    public void setMinArea(Double minArea) {
        this.minArea = minArea;
    }

    public Double getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Double maxArea) {
        this.maxArea = maxArea;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(Integer minFloor) {
        this.minFloor = minFloor;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * 区域
     */
    private String region;

    /**
     * 片区
     */
    private String regionDetail;

    /**
     * 小区
     */
    private String neighbourhood;

    /**
     * 室
     */
    private Integer room;

    /**
     * 厅
     */
    private Integer hall;

    /**
     * 最小面积
     */
    private Double minArea;

    /**
     * 最大面积
     */
    private Double maxArea;

    /**
     * 最低价
     */
    private Double minPrice;

    /**
     * 最高价
     */
    private Double maxPrice;

    /**
     * 最低楼层
     */
    private Integer minFloor;

    /**
     * 最高楼层
     */
    private Integer maxFloor;

    /**
     *
     */
    private String decoration;

    /**
     * 朝向
     */
    private String direction;

    /**
     * 目的
     */
    private String purpose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAbId() {
        return abId;
    }

    public void setAbId(Long abId) {
        this.abId = abId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
