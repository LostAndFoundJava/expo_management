package com.honger.expo.utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenjian on 2018/5/12.
 */
public class CacheUtils {
    private ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<>();
    private CacheUtils(){

    }

    private static class Holder{
        private final static CacheUtils cacheUtils = new CacheUtils();
    }

    public static CacheUtils getCacheSingleton(){
        return Holder.cacheUtils;
    }

    public ConcurrentHashMap<String, Object> getConcurrentHashMap() {
        return concurrentHashMap;
    }

    public void setConcurrentHashMap(ConcurrentHashMap<String, Object> concurrentHashMap) {
        this.concurrentHashMap = concurrentHashMap;
    }
}
