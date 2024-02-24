package com.xuecheng.content.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * @author Administrator
 * @version 1.0
 *
 * 令牌配置。发送和接收令牌策略配置
 **/
@Configuration
public class TokenConfig {

//    @Autowired
//    TokenStore tokenStore;
//
//    @Bean
//    public TokenStore tokenStore() {
//        //使用内存存储令牌（普通令牌）
//        return new InMemoryTokenStore();
//    }
//
//    //令牌管理服务
//    @Bean(name = "authorizationServerTokenServicesCustom")
//    public AuthorizationServerTokenServices tokenService() {
//        DefaultTokenServices service = new DefaultTokenServices();
//        service.setSupportRefreshToken(true);//支持刷新令牌
//        service.setTokenStore(tokenStore);//令牌存储策略
//        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
//        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
//        return service;
//    }


    // 配置JWT令牌生成策略

    // 密钥
    private String SIGNING_KEY = "mq123";
    @Autowired
    TokenStore tokenStore;
    // @Bean
// public TokenStore tokenStore() {
// //使用内存存储令牌（普通令牌）
// return new InMemoryTokenStore();
// }
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
    //令牌管理服务
    @Bean(name="authorizationServerTokenServicesCustom")
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setSupportRefreshToken(true);//支持刷新令牌
        service.setTokenStore(tokenStore);//令牌存储策略
        TokenEnhancerChain tokenEnhancerChain = new
                TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期 2 小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期 3 天
        return service;
    }



}
