package com.wuhulala;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wuhulala.util.FastJsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 配置json转换器
 *
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/18
 */
@Configuration
public class JsonConfig extends WebMvcConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(JsonConfig.class);
    /**
     * Json Converter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        logger.info("初始化 JSON Convert");
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        ValueFilter valueFilter = (_class, key, value) -> {
            if (null == value || "null".equals(value)){
                value = "";
            }
            return value;
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
    }

    /**
     * Redis Json-Serializer
     */
    @Bean
    public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object>template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }

    /**
     * Redis OpsValues
     */
    @Bean
    public ValueOperations<String,Object>valueOperations(RedisTemplate<String,Object>redisTemplate){
        return redisTemplate.opsForValue();
    }
}
