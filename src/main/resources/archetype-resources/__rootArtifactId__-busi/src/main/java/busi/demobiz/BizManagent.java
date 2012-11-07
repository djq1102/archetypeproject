package ${package}.busi.demobiz;

import java.math.BigDecimal;

import ${package}.core.orm.Page;
import ${package}.domain.option.DemoBizOption;
import ${package}.domain.pojo.DemoBiz;

public interface BizManagent {
    /**
     * 新增商户{@link Biz}
     * @param biz
     * @return <code>true/false</code>
     */
    public boolean insertBiz(DemoBiz biz);
    
    /**
     * 修改商户{@link Biz}
     * @param biz
     * @return <code>true/false</code>
     */
    public boolean updateBiz(DemoBiz biz);
    
    /**
     * 
     * @param id
     * @return
     */
    public DemoBiz getBizByBizId(BigDecimal id);
    
    /**
     * 
     * 根据查询条件分页查询商户
     * @param option
     *          查询条件
     * @param pageSiz
     *          页条数
     * @param pageNo
     *           页码
     * @return
     */
    public Page<DemoBiz> getBizListByOption(DemoBizOption biz);

}
