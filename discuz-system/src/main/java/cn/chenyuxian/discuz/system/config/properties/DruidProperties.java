/*
Copyright [2020] [https://www.stylefeng.cn]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Guns采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Guns源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/stylefeng/guns-separation
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/stylefeng/guns-separation
6.若您的项目无法满足以上几点，可申请商业授权，获取Guns商业授权许可，请在官网购买授权，地址为 https://www.stylefeng.cn
 */
package cn.chenyuxian.discuz.system.config.properties;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>数据库数据源配置</p>
 * <p>说明:类中属性包含默认值的不要在这里修改,应该在"application.yml"中配置</p>
 *
 * @author stylefeng
 * @date 2017/5/21 11:18
 */
@Data
@Slf4j
public class DruidProperties {

    /**
     * oracle校验语句
     */
    private final String ORACLE_VALIDATE_QUERY_SQL = "select 1 from dual";

    /**
     * postgresql校验语句
     */
    private final String POSTGRESQL_VALIDATE_QUERY_SQL = "select version()";

    /**
     * sqlserver校验语句
     */
    private final String SQLSERVER_VALIDATE_QUERY_SQL = "select 1";

    /**
     * mysql校验语句
     */
    private final String MYSQL_VALIDATE_QUERY_SQL = "select 1";

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Integer initialSize = 2;

    private Integer minIdle = 1;

    private Integer maxActive = 20;

    private Integer maxWait = 60000;

    private Integer timeBetweenEvictionRunsMillis = 60000;

    private Integer minEvictableIdleTimeMillis = 300000;

    private String validationQuery;

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = true;

    private Boolean testOnReturn = true;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String filters = "stat,wall";

    private String dataSourceName;

    public void config(DruidDataSource dataSource) {

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setDriverClassName(driverClassName);
        //定义初始连接数
        dataSource.setInitialSize(initialSize);
        //最小空闲
        dataSource.setMinIdle(minIdle);
        //定义最大连接数
        dataSource.setMaxActive(maxActive);
        //最长等待时间
        dataSource.setMaxWait(maxWait);

        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(getValidateQueryByUrl(url));
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        //打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            log.error(">>> 数据库连接池初始化异常：{}", e.getMessage());
        }
    }

    public Properties createProperties() {
        Properties properties = new Properties();
        properties.put("url", this.url);
        properties.put("username", this.username);
        properties.put("password", this.password);
        properties.put("driverClassName", this.driverClassName);
        properties.put("initialSize", this.initialSize);
        properties.put("maxActive", this.maxActive);
        properties.put("minIdle", this.minIdle);
        properties.put("maxWait", this.maxWait);
        properties.put("poolPreparedStatements", this.poolPreparedStatements);
        properties.put("maxPoolPreparedStatementPerConnectionSize", this.maxPoolPreparedStatementPerConnectionSize);
        properties.put("validationQuery", getValidateQueryByUrl(this.url));
        properties.put("testOnBorrow", this.testOnBorrow);
        properties.put("testOnReturn", this.testOnReturn);
        properties.put("testWhileIdle", this.testWhileIdle);
        properties.put("timeBetweenEvictionRunsMillis", this.timeBetweenEvictionRunsMillis);
        properties.put("minEvictableIdleTimeMillis", this.minEvictableIdleTimeMillis);
        properties.put("filters", this.filters);
        return properties;
    }

    private String getValidateQueryByUrl(String url) {
        if (url.contains(DbType.ORACLE.getDb())) {
            return ORACLE_VALIDATE_QUERY_SQL;
        } else if (url.contains(DbType.POSTGRE_SQL.getDb())) {
            return POSTGRESQL_VALIDATE_QUERY_SQL;
        } else if (url.contains(DbType.SQL_SERVER.getDb())) {
            return SQLSERVER_VALIDATE_QUERY_SQL;
        } else {
            return MYSQL_VALIDATE_QUERY_SQL;
        }
    }

}
