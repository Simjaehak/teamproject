package ksmart38.mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(value="ksmart38.mybatis.dao", sqlSessionFactoryRef="mybatisSqlSessionFactory")
@EnableTransactionManagement
public class MybatisConfig {
	
	
	//1.datasource DBCP(Hikari pool 설정)
	@Bean(name="hikariDataSource")
	public DataSource datasource(@Qualifier("jasyptStringEncryptor") StringEncryptor stringEncryptor ) {
		HikariConfig hikariConfig = new HikariConfig();
		
		  String jdbcUrl = stringEncryptor.decrypt("FiUQv3ldyEK6brIKJFHq3E8N87CPi36T34nf+3Ys+NJMga4wSJk24I/xpW5/02RkzL9XUM6Xzzofc9YRaYUVEUvtbSE9oZbNwzzaxTSatWh7LwKtJ5eGZyAi2niOJW68Fzwm62da/IJyv4vjIU77nw==");
		  String userName = stringEncryptor.decrypt("1Lx7hMn2XK6W4QBcfUyWFUWzQfUP/U/n");
		  String passWord = stringEncryptor.decrypt("kjGrp4LOdXKIu2D40kpMdFABrqZWLoyF");
		//클래스에 노출이 안되도록 함
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(passWord);
		hikariConfig.setMaximumPoolSize(15);
		hikariConfig.setMaxLifetime(1000*60*30);
		return new HikariDataSource(hikariConfig);
	}
	
	
	//2.Mybatis 연동을 위한 mybatisSqlSessionFactory 설정
	@Bean(name="mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("hikariDataSource") DataSource dataSource, ApplicationContext context) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("ksmart38.mybatis.domain");
		return sqlSessionFactoryBean.getObject();
	}
	
	//2.Mybatis SqlSessionTemplate 설정
	@Bean(name="mybatisSqlSessionTemplate")
	public SqlSessionTemplate mybatisSqlSessionTemplate(
			@Qualifier("mybatisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	//3.Mybatis TrancationManager 설정
	@Bean(name="mybatisTrancationManager")
	public PlatformTransactionManager trancationManager(@Qualifier("hikariDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
