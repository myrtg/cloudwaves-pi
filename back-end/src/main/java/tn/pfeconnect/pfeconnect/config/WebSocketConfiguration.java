package tn.pfeconnect.pfeconnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSocket communication using Spring's WebSocket support.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration  implements WebSocketMessageBrokerConfigurer{



    /**
     * Register Stomp endpoints for WebSocket communication.
     *
     * @param registry The StompEndpointRegistry used to register WebSocket endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
                .setAllowedOriginPatterns("*");
                //.withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new HttpHandshakeInterceptor());    }


    /**
     * Configure message broker options for WebSocket communication.
     *
     * @param registry The MessageBrokerRegistry used to configure message broker options.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
