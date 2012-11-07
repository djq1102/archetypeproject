package ${package}.webservice.rs.base;


import org.restlet.resource.Options;

/**
 * 基础resource
 * @author wangyihui
 *
 */
public interface BaseResource {
	
	@Options("form:json|xml")
	public void doOptions();

}
