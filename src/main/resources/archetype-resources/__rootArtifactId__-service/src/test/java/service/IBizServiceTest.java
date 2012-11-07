package ${package}.service;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ${package}.core.orm.Page;
import ${package}.domain.pojo.DemoBiz;
import ${package}.domain.pojo.DemoBizExample;
import ${package}.service.demobiz.BizService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class IBizServiceTest {
    
    @Autowired
    BizService bizService;

    @Test
    public void testInsertBiz() {
        DemoBiz biz = new DemoBiz();
      biz.setAddress("address");
      biz.setArea("area");
      biz.setEmail("email"+RandomUtils.nextInt());
      biz.setName("scott");
      biz.setMobile("12333333"+RandomUtils.nextInt());
      Assert.assertTrue(bizService.insertBiz(biz));
      
      DemoBiz insertedBiz= bizService.getBizByBizId(biz.getId());
      Assert.assertTrue(insertedBiz.getAddress().equals(biz.getAddress()));
      Assert.assertTrue(insertedBiz.getArea().equals(biz.getArea()));
      Assert.assertTrue(insertedBiz.getEmail().equals(biz.getEmail()));
      Assert.assertTrue(insertedBiz.getMobile().equals(biz.getMobile()));
    }
    
    @Rollback(true)
    @Test
    public void testInsertWithRollback() {
        DemoBiz biz = new DemoBiz();
        biz.setAddress("address");
        biz.setArea("area");
        biz.setEmail("email"+RandomUtils.nextInt());
        biz.setName("scott");
        biz.setMobile("12333333"+RandomUtils.nextInt());
        Assert.assertTrue(bizService.insertBiz(biz));
    }

    @Test
    public void testUpdateBiz() {
        DemoBiz biz = new DemoBiz();
        biz.setAddress("address");
        biz.setArea("area");
        biz.setEmail("email"+RandomUtils.nextInt());
        biz.setName("scott");
        biz.setMobile("12333333"+RandomUtils.nextInt());
        //插入Biz
        Assert.assertTrue(bizService.insertBiz(biz));
        
        //获取插入的biz
        DemoBiz insertedBiz= bizService.getBizByBizId(biz.getId());
        String address = "updateaddress";
        //修改biz内容
        insertedBiz.setAddress(address);
        //更新Biz
        Assert.assertTrue(bizService.updateBiz(insertedBiz));
        
        //获取更新后Biz
        DemoBiz updatedBiz = bizService.getBizByBizId(biz.getId());
        //比较更新后字段
        Assert.assertTrue(updatedBiz.getAddress().equals(address));
    }

    @Test
    public void testGetBizListByOption() {
        DemoBizExample option = new DemoBizExample();
        Page<DemoBiz> bizPage = bizService.getBizListByOption(option, 15, 1);
        Assert.assertTrue(bizPage.getResult().size() > 0);
    }
    
    @Test
    public void testGetBizByBizId(){
        DemoBiz biz = new DemoBiz();
        biz.setAddress("address");
        biz.setArea("area");
        biz.setEmail("email"+RandomUtils.nextInt());
        biz.setName("scott");
        biz.setMobile("12333333"+RandomUtils.nextInt());
        Assert.assertTrue(bizService.insertBiz(biz));
        
        DemoBiz insertedBiz= bizService.getBizByBizId(biz.getId());
        Assert.assertTrue(insertedBiz.getAddress().equals(biz.getAddress()));
        Assert.assertTrue(insertedBiz.getArea().equals(biz.getArea()));
        Assert.assertTrue(insertedBiz.getEmail().equals(biz.getEmail()));
        Assert.assertTrue(insertedBiz.getMobile().equals(biz.getMobile()));
    }

}
