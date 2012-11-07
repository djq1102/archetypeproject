package ${package}.webservice.rs.base.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.resource.ServerResource;

import ${package}.webservice.rs.base.BaseResource;

/**
 * 
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: BaseResourceImpl.java 4 2012-05-30 06:16:01Z djq $ 
 * @since 2.0
 */
public class BaseResourceImpl extends ServerResource implements BaseResource {
	/**
	 * get login name, if this url is under protection.
	 * 
	 * @return String of login name
	 * @see HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return ServletUtils.getRequest(getRequest()).getRemoteUser();
	}


	@Override
	public void doOptions() {
		Form responseHeaders = (Form) getResponse().getAttributes().get("org.restlet.http.headers");
		if (responseHeaders == null) {
			responseHeaders = new Form();
			getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders);
		}
		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
		responseHeaders.add("Access-Control-Allow-Credentials", "false");
		responseHeaders.add("Access-Control-Max-Age", "60");
	}

	public void back2Page() {
		Form responseHeaders = (Form) getResponse().getAttributes().get("org.restlet.http.headers");
		if (responseHeaders == null) {
			responseHeaders = new Form();
			getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders);
		}
		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Content-Type", "Application/json");
	}

	/**
	 * 
	 * 获取IP
	 * @param request
	 * @return
	 */
	public String getRealIp(Request request) {
		HttpServletRequest servletRequest = ServletUtils.getRequest(getRequest());
		return StringUtils.isEmpty(servletRequest.getHeader("X-Real-IP")) ? getRequest().getClientInfo().getAddress()
				: servletRequest.getHeader("X-Real-IP");
	}
}
