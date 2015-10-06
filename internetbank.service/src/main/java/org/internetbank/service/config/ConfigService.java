package org.internetbank.service.config;

import org.internetbank.springdao.config.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("org.internetbank.service")
@Import(DataConfig.class) 
public class ConfigService {

}
