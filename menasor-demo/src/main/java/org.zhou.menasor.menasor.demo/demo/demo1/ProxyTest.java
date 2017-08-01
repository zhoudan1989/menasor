package org.zhou.menasor.menasor.demo.demo.demo1;

/**
 * Created by DT283 on 2017/7/3.
 */
public class ProxyTest {

    public static void main(String[] args) {
        IUserDao invoker = (IUserDao) new Invoker().getInstance(IUserDao.class);
        System.out.println(invoker.getUserName());
    }

}
