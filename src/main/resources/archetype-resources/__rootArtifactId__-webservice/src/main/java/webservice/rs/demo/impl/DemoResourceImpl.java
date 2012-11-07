package ${package}.webservice.rs.demo.impl;

import org.apache.log4j.Logger;
import org.restlet.data.Form;
import org.restlet.resource.ResourceException;

import ${package}.domain.pojo.DemoBiz;
import ${package}.webservice.rs.base.impl.BaseResourceImpl;
import ${package}.webservice.rs.demo.DemoResource;

public class DemoResourceImpl extends BaseResourceImpl implements DemoResource{
   
    private static Logger logger = Logger.getLogger(DemoResourceImpl.class);
   
    private Form          form;


    @Override
    protected void doInit() throws ResourceException {
        form = getRequest().getResourceRef().getQueryAsForm();
    }


    @Override
    public DemoBiz getString() {
        logger.info("use logger to log infomation");
        String userName = form.getFirstValue("userName");
        DemoBiz biz = new DemoBiz();
        biz.setName(userName);
        return biz;
    }
}
