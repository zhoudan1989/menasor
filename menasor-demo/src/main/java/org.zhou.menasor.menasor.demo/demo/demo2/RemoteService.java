package org.zhou.menasor.menasor.demo.demo.demo2;

/**
 * Created by DT283 on 2017/7/3.
 */
public class RemoteService implements Service {
    @Override
    public String getService(String name, int number) {
        return name + ":" + number;
    }
}
