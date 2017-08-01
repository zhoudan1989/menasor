package org.zhou.menasor.menasor.filter;

/**
 * Created by DT283 on 2017/7/10.
 */
public interface Filter {

    boolean doFilter(String kafkaKey, String kafkaInfo);

}
