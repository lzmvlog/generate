package com.shaojie.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author： ShaoJie
 * @data： 2020年01月23日 20:36
 * @Description： 配置数据库连接属性
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProperties {

}
