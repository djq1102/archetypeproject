package ${package}.core.orm.mybatis.dialect;


/**
 * 
 * mybatis分页使用的方言抽象类
 * @see OracleDialect
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: Dialect.java 3 2012-05-30 03:27:37Z djq $ 
 * @since 2.0
 */
public abstract class Dialect {

	public static enum Type{
		MYSQL,
		ORACLE
	}
	
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
	
}
