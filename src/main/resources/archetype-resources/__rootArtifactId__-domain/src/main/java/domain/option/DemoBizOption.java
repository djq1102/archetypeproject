package ${package}.domain.option;

import ${package}.domain.pojo.DemoBiz;

public class DemoBizOption extends DemoBiz{

    
     /**    */
    private static final long serialVersionUID = -8922673753782484258L;

    private int pageNo;
    
    private int pageSize;

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
}
