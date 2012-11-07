package ${package}.service.demobiz;

import java.math.BigDecimal;

import ${package}.core.orm.Page;
import ${package}.domain.pojo.DemoBiz;
import ${package}.domain.pojo.DemoBizExample;

/**
 * 
 * 商户服务接口
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: BizService.java 3 2012-05-30 03:27:37Z djq $ 
 * @since 2.0
 */
public interface BizService {
    
    /**
     * 新增商户{@link DemoBiz}
     * @param biz
     * @return <code>true/false</code>
     */
    public boolean insertBiz(DemoBiz biz);
    
    /**
     * 修改商户{@link DemoBiz}
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
    public Page<DemoBiz> getBizListByOption(DemoBizExample option,int pageSize,int pageNo);
}
