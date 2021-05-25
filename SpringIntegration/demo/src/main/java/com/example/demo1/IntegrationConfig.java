package com.example.demo1;

import com.example.demo1.services.LoggerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
//    @Bean
//    public MessageChannel testChannel() {
//        return new DirectChannel();
//    }

    @Bean
    public MessageChannel testChannel() {
        return MessageChannels.direct("testChannel").get();
    }

    @Bean
    public IntegrationFlow integrationFlow(LoggerService loggerService) {
        return IntegrationFlows.from("testChannel")
                .handle(loggerService, "logIt")
                .get();
    }

}
