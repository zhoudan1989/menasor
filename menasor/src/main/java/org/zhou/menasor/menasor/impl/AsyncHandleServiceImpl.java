package org.zhou.menasor.menasor.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhou.menasor.async.consumer.service.MessageHandleService;
import org.zhou.menasor.async.serialization.dto.AsyncDTO;
import org.zhou.menasor.menasor.spring.InvokeExecute;

public class AsyncHandleServiceImpl implements MessageHandleService<String, AsyncDTO> {
    private final static Logger logger = LogManager.getLogger(AsyncHandleServiceImpl.class);

    private Object service;

/*    private List<Filter> filters = new ArrayList<Filter>();*/

    public AsyncHandleServiceImpl(Object object) {
        service = object;
    }

/*    public void addFilter(Filter filter){
        filters.add(filter);
    }*/

    public void doAction(String kafkaKey, AsyncDTO kafkaInfo) {
        try {/*
            if(!filters.isEmpty()){
                for(Filter filter:filters){
                    if(!filter.doFilter(kafkaKey, kafkaInfo)){
                        throw new Exception("filter is fails!");
                    }
                }
            }*/
            InvokeExecute.execute(service, kafkaInfo.getMethod(),
                    kafkaInfo.getParameters(),
                    kafkaInfo.getParameterTypes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}