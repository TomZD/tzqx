package cn.movinginfo.tztf.sen.domain;

import java.util.List;
/**
 * 分页
 * @author wqb
 *
 * @param <T>
 */
public class PageModel<T> {

	 /***
     * 当前页
     */
    private int pageNum = 1;
 
    /***
     * 总页数
     */
    public int pages = 0;
 
    /***
     * 每页数据条数
     */
    private int pageSize;
 
    /***
     * 总页数
     */
    private int total = 0;
 
    /***
     * 每页的起始数
     */
    private int startRow = 0;
 
    /***
     * 每页显示数据的终止数
     */
    private int endRow = 0;
 
    /***
     * 是否有下一页
     */
    private boolean hasNextPage = false;
 
    /***
     * 是否有前一页
     */
    private boolean hasPreviousPage = false;
 
    /***
     * 数据集合
     */
    private List<T> list;
 
 
    public PageModel(List<T> list, int pageRecorders) {
        // 通过对象集，记录总数划分
        init(list, pageRecorders);
    }
 
    /** *//**
     * 初始化list，并告之该list每页的记录数
     * @param list 数据几个
     * @param pageRecorders 一页显示多少数据
     */
    public void init(List<T> list, int pageRecorders) {
        this.pageSize = pageRecorders;
        this.list = list;
        total = list.size();
        hasPreviousPage = false;
        if ((total % pageRecorders) == 0) {
        	pages = total / pageRecorders;
        } else {
        	pages = total / pageRecorders + 1;
        }
 
        if (pageNum >= pages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
 
        if (total < pageRecorders) {
            this.startRow = 0;
            this.endRow = total;
        } else {
            this.startRow = 0;
            this.endRow = pageRecorders;
        }
    }
 
 
    // 判断要不要分页
    public boolean isNext() {
        return list.size() > 5;
    }
 
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
 
    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }
 
    public void description() {
 
        String description = "共有数据数:" + this.getTotalRows() +
 
                "共有页数: " + this.getTotalPages() +
 
                "当前页数为:" + this.getPage() +
 
                " 是否有前一页: " + this.isHasPreviousPage() +
 
                " 是否有下一页:" + this.isHasNextPage() +
 
                " 开始行数:" + this.getPageStartRow() +
 
                " 终止行数:" + this.getPageEndRow();
 
        System.out.println(description);
    }
 
    public List getNextPage() {
    	pageNum = pageNum + 1;
 
        disposePage();
 
        System.out.println("用户凋用的是第" + pageNum + "页");
        this.description();
        return getObjects(pageNum);
    }
 
    /**
     * 处理分页
     */
    private void disposePage() {
 
        if (pageNum == 0) {
        	pageNum = 1;
        }
 
        if ((pageNum - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }
 
        if (pageNum >= pages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
    }
 
    public List getPreviousPage() {
 
    	pageNum = pageNum - 1;
 
        if ((pageNum - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }
        if (pageNum >= pages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        this.description();
        return getObjects(pageNum);
    }
 
    /**
     * 获取第几页的内容
     *
     * @param page 当前页面
     * @return
     */
    public List<T> getObjects(int page) {
        if(page == 0){
            this.setPage(1);
        }
        else{
            this.setPage(page);
        }
        this.disposePage();
        if (page * pageSize < total) {
            // 判断是否为最后一页
        	endRow = page * pageSize;
            startRow = endRow - pageSize;
        } else {
        	endRow = total;
            startRow = pageSize * (pages - 1);
        }
 
        List<T> objects = null;
        if (!list.isEmpty()) {
            objects = list.subList(startRow, endRow);
        }
        //this.description();
        return objects;
    }
 
    public List<T> getFistPage() {
        if (this.isNext()) {
            return list.subList(0, pageSize);
        } else {
            return list;
        }
    }
 
    public boolean isHasNextPage() {
        return hasNextPage;
    }
 
 
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
 
 
    public List getList() {
        return list;
    }
 
 
    public void setList(List list) {
        this.list = list;
    }
 
 
    public int getPage() {
        return pageNum;
    }
 
 
    public void setPage(int page) {
        this.pageNum = page;
    }
 
 
    public int getPageEndRow() {
        return endRow;
    }
 
 
    public void setPageEndRow(int pageEndRow) {
        this.endRow = pageEndRow;
    }
 
 
    public int getPageRecorders() {
        return pageSize;
    }
 
 
    public void setPageRecorders(int pageRecorders) {
        this.pageSize = pageRecorders;
    }
 
 
    public int getPageStartRow() {
        return startRow;
    }
 
 
    public void setPageStartRow(int pageStartRow) {
        this.startRow = pageStartRow;
    }
 
 
    public int getTotalPages() {
        return pages;
    }
 
 
    public void setTotalPages(int totalPages) {
        this.pages = totalPages;
    }
 
 
    public int getTotalRows() {
        return total;
    }
 
 
    public void setTotalRows(int totalRows) {
        this.total = totalRows;
    }
 
 
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
}
