package com.mk.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

@Configuration
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean
    public DataSourceProxy dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }


//    @Bean
//    @ConfigurationProperties(prefix = "mybatis-plus")
//    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
//        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
//        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        factoryBean.setPlugins(new Interceptor[]{paginationInterceptor});
//        factoryBean.setDataSource(dataSourceProxy);
//        factoryBean.setTypeAliasesPackage("com.mk.entity");
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:mapper/**/*.xml"));
//        factoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
//        factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        return factoryBean.getObject();
//    }

}
