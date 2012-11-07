package ${package}.webservice.rs.demo;

import org.restlet.resource.Get;

import ${package}.domain.pojo.DemoBiz;


/**
 * 
 * demo rest source
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: DemoResource.java 4 2012-05-30 06:16:01Z djq $ 
 * @since 2.0
 */
public interface DemoResource{

    /**
     * 使用/rs/demo
     * @return
     */
	@Get
	public DemoBiz getString();
	
}
