package com.fork.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class druidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();

    }

    // 配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //还可以顺便往map里添加一些配置信息
        Map<String, String> initParms = new HashMap<String,String>();

        initParms.put("logingUserName","admin7");
        initParms.put("loginPassWord","123456");
        initParms.put("allow","");
        initParms.put("deny","127.0.0.1");

        bean.setInitParameters(initParms);
        return bean;
    }

    //还要配置一个filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParms = new HashMap<String,String>();
        initParms.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParms);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;

    }

}
