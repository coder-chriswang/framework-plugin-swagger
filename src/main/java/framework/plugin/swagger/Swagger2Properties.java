package framework.plugin.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>
 * 名称: Swagger2Properties
 * 描述: swagger2属性类
 * </pre>
 * @author Chris(wangchao)
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "swagger.chris")
public class Swagger2Properties {

    private String basePackage;

    private String title;

    private String description;

    private String version;

    private String termsOfServiceUrl;

    private String contact;
}
