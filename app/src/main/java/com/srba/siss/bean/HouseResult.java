package com.srba.siss.bean;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/6
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/6         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HouseResult {

    /**
     * result : 200
     * msg : SUCCESS
     * data : {"offset":0,"pageNo":1,"pageSize":10,"result":[{"id":1,"asId":1,"houseCardNo":"2","houseInfoNo":"3","neighbourhood":"小区","region":"区域","regionDetail":"片区","building":"楼盘","buildingNo":8,"floor":3,"houseType":"户型","area":"面积","price":0,"averagePrice":0,"decoration":"装修程度","direction":"朝向"}],"totalCount":1,"totalPages":1,"first":1}
     */

    private int result;
    private String msg;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * offset : 0
         * pageNo : 1
         * pageSize : 10
         * result : [{"id":1,"asId":1,"houseCardNo":"2","houseInfoNo":"3","neighbourhood":"小区","region":"区域","regionDetail":"片区","building":"楼盘","buildingNo":8,"floor":3,"houseType":"户型","area":"面积","price":0,"averagePrice":0,"decoration":"装修程度","direction":"朝向"}]
         * totalCount : 1
         * totalPages : 1
         * first : 1
         */

        private int offset;
        private int pageNo;
        private int pageSize;
        private int totalCount;
        private int totalPages;
        private int first;
        private List<HouseResource> result;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public List<HouseResource> getResult() {
            return result;
        }

        public void setResult(List<HouseResource> result) {
            this.result = result;
        }
    }
}
