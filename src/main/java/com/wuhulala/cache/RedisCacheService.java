/*
 *
 *  * Copyright 2014-2017 Wuhulala.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.wuhulala.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存客户端
 * <p>
 * author： wuhulala
 * date： 2017/6/11
 * version: 1.0
 * description: redis实现缓存服务
 */
@Component
public class RedisCacheService<K, V> implements CacheService<K, V> {

    @Resource(name = "redisTemplate")
    private RedisTemplate<K, V> redisTemplate;

    @Resource(name = "valueOperations")
    private ValueOperations<K, V> valueOperations;

    @Override
    public void init() {
        System.out.println("i do not need to init it...");
    }

    @Override
    public V getValue(K key) {
        return valueOperations.get(key);
    }

    @Override
    public void setValue(K key, V value) {
        valueOperations.set(key, value);
    }

    @Override
    public void setValue(K key, V value, long expire) {
        valueOperations.set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void delValue(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void flushDB() {
        System.out.println("清空缓存数据库？ 臣妾做不到....");
    }

    @Override
    public Long dbSize() {
        return null;
    }

    @Override
    public Set<K> keys(String pattern) {
        return null;
    }

    @Override
    public long getExpireSeconds(K key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
