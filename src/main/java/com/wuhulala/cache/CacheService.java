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

import java.util.Set;

/**
 * cache接口
 *
 * author： wuhulala
 * date： 2017/6/11
 * version: 1.0
 * description: 不同的客户端实现也必须使用此接口
 */
public interface CacheService<K,V> {
    /**
     * 初始化连接缓存客户端
     */
    public void init();

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    public V getValue(K key);

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    public void setValue(K key,V value);

    /**
     * set
     * @param key
     * @param value
     * @param expire 过期时间
     * @return
     */
    public void setValue(K key,V value,long expire);

    /**
     * del
     * @param key
     */
    public void delValue(K key);

    /**
     * flush 清空缓存
     */
    public void flushDB();

    /**
     * size
     */
    public Long dbSize();

    /**
     * 获取所有key
     * @return
     */
    public Set<K> keys(String pattern);

    public long getExpireSeconds(K key);
}
