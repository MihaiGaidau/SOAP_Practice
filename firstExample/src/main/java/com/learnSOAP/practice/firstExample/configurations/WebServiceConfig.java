package com.learnSOAP.practice.firstExample.configurations;

import com.learnSOAP.practice.firstExample.service.CourseDetailsService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringCertificateValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.security.auth.callback.CallbackHandler;
import java.security.KeyStore;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");

    }

    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("/static/course-details.xsd"));
    }

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace("http://mgaidau.com/SOAPpractice/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(schema);
        return  definition;
    }

    @Bean
    public XwsSecurityInterceptor securityInterceptor(){
        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setCallbackHandler(callbackHandler());
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("/static/securityPolicy.xml"));
        return  securityInterceptor;
    }

    @Bean
    public SimplePasswordValidationCallbackHandler callbackHandler() {
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Collections.singletonMap("user","password"));
        return handler;
    }
// A password may be given to check the integrity of the keystore data.
// If a password is not given, integrity checking is not performed.
//    @Bean
//    public KeyStoreFactoryBean keyStore(){
//        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
//        keyStoreFactoryBean.setPassword("password");
//        keyStoreFactoryBean.setLocation(new ClassPathResource("test-keystore.jks"));
//        return keyStoreFactoryBean;
//    }

    //For instance, if you want to use the KeyStoreCallbackHandler to validate incoming
    // certificates or signatures, you would use a trust store, like so:
//    @Bean
//    public KeyStoreCallbackHandler keyStoreHandler(){
//        KeyStoreCallbackHandler handler = new KeyStoreCallbackHandler();
//        handler.setTrustStore(trustStore().getObject());
//        return handler;
//    }
//
//    @Bean
//    public KeyStoreFactoryBean trustStore(){
//        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
//        keyStoreFactoryBean.setLocation(new ClassPathResource("truststore.jks"));
//        keyStoreFactoryBean.setPassword("password");
//        return trustStore();
//    }


//    @Bean
//    public SpringCertificateValidationCallbackHandler certificateHandlerHandler(){
//        SpringCertificateValidationCallbackHandler handler = new SpringCertificateValidationCallbackHandler();
//        return handler;
//    }
//
//    @Bean
//    public XwsSecurityInterceptor securityInterceptorCertificate(){
//        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
//        securityInterceptor.setCallbackHandler(certificateHandlerHandler());
//        return securityInterceptor;
//    }

    //decrypt incoming certificates or sign outgoing messages, you would use a key store
    @Bean
    public KeyStoreCallbackHandler keyStoreHandler(){
        KeyStoreCallbackHandler handler = new KeyStoreCallbackHandler();
        handler.setKeyStore(keyStore().getObject());
        handler.setPrivateKeyPassword("changeIt");
        return handler;

    }
//
    @Bean
    public KeyStoreFactoryBean keyStore() {
        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
        keyStoreFactoryBean.setLocation(new ClassPathResource("keystore.jks"));
        keyStoreFactoryBean.setPassword("changeIt");
        return keyStoreFactoryBean;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }


}
