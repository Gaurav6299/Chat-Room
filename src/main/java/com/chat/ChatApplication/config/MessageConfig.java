package com.chat.ChatApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  //ye annotaion message broker ko enable kar dega.
public class MessageConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        Yaha Hme apna server configure karna hoga, kyuki agar koi connect karna chahega to server ke kis url par
//        connectivity hogi.
        registry.addEndpoint("/server").withSockJS(); //This will allow you to use http://localhost:8080/server to establish websocket connection
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");  //yaha hmne /topic url to enable kar diya hai so iske baad jo bhi url
        //messagecontroller me hoga like /topic/return-to so wo hit ho jayega.
        registry.setApplicationDestinationPrefixes("/chat");   //ye destination prefix use hoga message send karne ke time me jab mera message
        //controller ka /message url hit uske pahale /chat as a prefix hoga.
    }
}
