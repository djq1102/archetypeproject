package ${package}.core.orm.mybatis.dialect;

import ${package}.core.orm.mybatis.interceptor.PaginationInterceptor;

/**
 * 
 * mybatis通过{@link PaginationInterceptor}拦截到sql，对sql进行oracle的分页处理
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: OracleDialect.java 3 2012-05-30 03:27:37Z djq $ 
 * @since 2.0
 */
public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sql);
		
		pagingSelect.append(" ) row_ ) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}
}
