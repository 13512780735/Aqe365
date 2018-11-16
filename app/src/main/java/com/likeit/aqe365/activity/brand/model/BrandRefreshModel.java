package com.likeit.aqe365.activity.brand.model;

import java.util.List;

public class BrandRefreshModel {

    /**
     * issuccess : 1
     * data : [{"id":"9","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/unQ52qyPc55uYQYYYpzdy6Dyp434D6.jpg"},{"id":"8","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/OJK5WMik5kJsh5UI1I6ZDwQjIT1jXD.jpg"},{"id":"7","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/nktvRvyId38CZVrDV7vI78rYy9vvya.jpg"},{"id":"6","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/MCyqz7oqYcH9aXl9cIlxHGKipHyCQa.jpg"},{"id":"5","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/dLd7sJ7ppmPYv3sFJNcpmlaPrf7PmC.jpg"},{"id":"3","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/jGZXkRZWnhHNnHHnz8R8GxNmr8wqZw.jpg"},{"id":"4","advimg":"http://aoquan.maimaitoo.com/attachment/images/1/2018/06/jGZXkRZWnhHNnHHnz8R8GxNmr8wqZw.jpg"}]
     * num : 3
     */

    private int issuccess;
    private String num;
    private List<LogoBean> data;

    public int getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(int issuccess) {
        this.issuccess = issuccess;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<LogoBean> getData() {
        return data;
    }

    public void setData(List<LogoBean> data) {
        this.data = data;
    }

}
