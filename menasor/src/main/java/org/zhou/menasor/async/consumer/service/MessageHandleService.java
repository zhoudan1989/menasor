package org.zhou.menasor.async.consumer.service;

public interface MessageHandleService<K, V> {
    void doAction(K Key, V Info);
}





