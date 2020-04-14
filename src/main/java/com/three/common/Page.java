package com.three.common;


import java.util.List;

public class Page<T> {
    private int totalRows;    //总行数
    private int current;    //当前页
    private int totalPages; //总页数
    private int pageSize;     // 每页行数
    private List<T> rows;     // 结果集，储存一个页面的数据记录
    private int[] navigatepageNums;    //所有导航页号
    private int navigatePages; //导航页码数，即滑动窗口中格子个数

    //导航条上的第一页
    private int navigateFirstPage;
    //导航条上的最后一页
    private int navigateLastPage;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;

    //构造函数
    public Page(int totalRows, int pageSize, int navigatePages, int current, List<T> rows) {
        //设置5个属性
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        this.navigatePages = navigatePages;
        this.current = current;
        this.rows = rows;
        // 设置6个属性
        this.calcProperties();
    }

    //计算滑动窗口中的页号，不止一种生成策略，这里采用
    //http://git.oschina.net/free/Mybatis_PageHelper提供的方案来实现
//总页码编号为1...totalPages。基本原理是：
//以当前页面为基点，在页码序列中各取导航页码数的一半来填充数组navigatepageNums。
    private int[] calcNavigatepageNums() {
        //当总页数小于或等于导航页码数时
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = current - navigatePages / 2;
            int endNum = current + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //前端超页时，从页码1开始填充数组navigatepageNums。
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {
                endNum = totalPages;
                //后端超页时，从最大页码开始逆序填充数组navigatepageNums。
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //前后都不超页时。
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        return navigatepageNums;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public boolean getIsFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    //设置6个属性
    private void calcProperties() {
        this.totalPages = (int) Math.ceil((double) totalRows / pageSize);
        this.isFirstPage = current == 1;
        this.isLastPage = current == totalPages || totalPages == 0;
        this.navigatepageNums = calcNavigatepageNums();
        //计算滑动窗口第一格的页号、最后一格页号
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            this.navigateFirstPage = navigatepageNums[0];
            this.navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
        }
    }
}