package com.hopeshop.order.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.hopeshop.order.properties.PayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;

/**
 * @author silence
 * @create 2020-01-27
 */
@Configuration
@EnableConfigurationProperties(PayProperties.class)
public class PayConfig extends WXPayConfig {

    @Autowired
    private PayProperties payProperties;

    public String getAppID() {
        return payProperties.getAppId();
    }
    public String getMchID() {
        return payProperties.getMchId();
    }
    public String getKey() {
        return payProperties.getKey();
    }
    public InputStream getCertStream() {
        return null;
    }

    public IWXPayDomain getWXPayDomain(){
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return payProperties.getConnectTimeoutMs();
    }
    public int getHttpReadTimeoutMs() {
        return payProperties.getReadTimeoutMs();
    }

}
