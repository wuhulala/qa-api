package com.wuhulala.util;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * fastjson 作为redis的序列化工具
 *
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/21
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    static final byte[] EMPTY_ARRAY = new byte[0];

    private final Class<T> javaType;

    public FastJsonRedisSerializer(Class<T> type) {
        this.javaType = type;
    }

    public T deserialize(byte[] bytes) throws SerializationException {
        if(isEmpty(bytes)) {
            return null;
        } else {
            try {
                return JSON.parseObject(bytes,this.javaType);
            } catch (Exception var3) {
                throw new SerializationException("Could not read JSON: " + var3.getMessage(), var3);
            }
        }
    }

    public byte[] serialize(Object t) throws SerializationException {
        if(t == null) {
            return EMPTY_ARRAY;
        } else {
            try {
                return JSON.toJSONBytes(t);
            } catch (Exception var3) {
                throw new SerializationException("Could not write JSON: " + var3.getMessage(), var3);
            }
        }
    }

    private boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }
}
