package br.com.agibank.analisadordedados.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.com.agibank.analisadordedados")
public class ScheduledConfig {
}
