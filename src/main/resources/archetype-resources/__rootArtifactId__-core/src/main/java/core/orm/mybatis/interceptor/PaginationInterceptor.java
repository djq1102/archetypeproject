package ${package}.core.orm.mybatis.interceptor;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import ${package}.core.orm.mybatis.dialect.Dialect;
import ${package}.core.orm.mybatis.dialect.OracleDialect;

 
/**
 * 
 * Mybatis翻页拦截器
 * @author </a href="mailto:dujq@llyuntong.com.cn">dujq</a>
 * @version $Id: PaginationInterceptor.java 3 2012-05-30 03:27:37Z djq $ 
 * @since 2.0
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{

 

    private final static Log log = LogFactory.getLog(PaginationInterceptor.class);

   

    @Override

    public Object intercept(Invocation invocation) throws Throwable {

       StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

       BoundSql boundSql = statementHandler.getBoundSql();

       MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

       RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");

       if(rowBounds == null || rowBounds == RowBounds.DEFAULT){

           return invocation.proceed();

       }

       Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");

       Dialect.Type databaseType  = null;

       try{

           databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());

       } catch(Exception e){

           //ignore

       }

       if(databaseType == null){

           throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));

       }

       Dialect dialect = null;

       switch(databaseType){

           case ORACLE:

              dialect = new OracleDialect();

       }

      

       String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");

       metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );

       metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );

       metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );

       if(log.isDebugEnabled()){

           log.debug("生成分页SQL : " + boundSql.getSql());

       }

       return invocation.proceed();

    }

 

    @Override

    public Object plugin(Object target) {

       return Plugin.wrap(target, this);

    }

 

    @Override

    public void setProperties(Properties properties) {

    }

 

}