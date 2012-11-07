package ${package}.busi.demobiz.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.busi.demobiz.BizManagent;
import ${package}.core.orm.Page;
import ${package}.domain.option.DemoBizOption;
import ${package}.domain.pojo.DemoBiz;
import ${package}.domain.pojo.DemoBizExample;
import ${package}.service.demobiz.BizService;

public class BizManagentImpl implements BizManagent {

    @Autowired
    private BizService bizService;

    @Override
    public boolean insertBiz(DemoBiz biz) {
        return bizService.insertBiz(biz);
    }

    @Override
    public boolean updateBiz(DemoBiz biz) {
        return bizService.updateBiz(biz);
    }

    @Override
    public DemoBiz getBizByBizId(BigDecimal id) {
        return bizService.getBizByBizId(id);
    }

    @Override
    public Page<DemoBiz> getBizListByOption(DemoBizOption biz) {
        DemoBizExample example = new DemoBizExample();
        example.createCriteria();
        if (null != biz) {
            if (null != biz.getName() && !"".equals(biz.getName())) {
                example.getOredCriteria().iterator().next().andNameLike("%" + biz.getName() + "%");
            }

            if (null != biz.getMobile() && !"".equals(biz.getMobile())) {
                example.getOredCriteria().iterator().next().andMobileEqualTo(biz.getMobile());
            }
        }
        return bizService.getBizListByOption(example, biz.getPageSize(), biz.getPageNo());
    }

}
