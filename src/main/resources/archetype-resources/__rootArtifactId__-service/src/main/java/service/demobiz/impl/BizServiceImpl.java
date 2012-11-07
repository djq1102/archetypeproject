package ${package}.service.demobiz.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.core.orm.Page;
import ${package}.core.service.BaseService;
import ${package}.domain.mapper.DemoBizMapper;
import ${package}.domain.pojo.DemoBiz;
import ${package}.domain.pojo.DemoBizExample;
import ${package}.service.demobiz.BizService;

public class BizServiceImpl extends BaseService implements BizService {
    
    @Autowired
    private DemoBizMapper demoBizMapper;

    @Override
    public boolean insertBiz(DemoBiz biz) {
        demoBizMapper.insert(biz);
        return demoBizMapper.insert(biz) > 0;
    }

    @Override
    public boolean updateBiz(DemoBiz biz) {
        return demoBizMapper.updateByPrimaryKeySelective(biz) > 0;
    }

    @Override
    public Page<DemoBiz> getBizListByOption(DemoBizExample option, int pageSize, int pageNo) {
        return queryPage(DemoBizMapper.class, option, pageNo, pageSize);
    }

    @Override
    public DemoBiz getBizByBizId(BigDecimal id) {
        return demoBizMapper.selectByPrimaryKey(id);
    }

}
