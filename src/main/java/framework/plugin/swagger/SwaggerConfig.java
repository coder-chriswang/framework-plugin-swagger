/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package framework.plugin.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 名称: SwaggerConfig
 * 描述: swagger配置
 * </pre>
 * @author Chris(wangchao)
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
@Profile({ "dev", "test" })
@Slf4j
public class SwaggerConfig implements EnvironmentAware {

    private Binder binder;

    private Swagger2Properties swagger2Properties;

    private static final String prefix = "swagger.chris";

    @Autowired(required = false)
    private List<Parameter> defaultParameters;

    @Override
    public void setEnvironment(final Environment environment) {
        this.binder = Binder.get(environment);
        this.swagger2Properties = binder.bind(prefix, Swagger2Properties.class).get();
    }

    /**
     * 装载swagger
     * @return Docket实例
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(defaultParameters != null && defaultParameters.size() > 0 ? defaultParameters : Collections.emptyList())
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                .paths(PathSelectors.any()).build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfo(swagger2Properties.getTitle(), swagger2Properties.getDescription(),
                swagger2Properties.getVersion(), swagger2Properties.getTermsOfServiceUrl(),
                swagger2Properties.getContact(),"","");
    }

}
