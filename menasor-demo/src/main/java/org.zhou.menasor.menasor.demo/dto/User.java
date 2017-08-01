package org.zhou.menasor.menasor.demo.dto;

/**
 * Created by DT283 on 2017/7/4.
 */
public class User /*implements Serializable*/ {
    private String name;
    private String passwd;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public User setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public String toString() {
        return "name:" + this.name + ",passwd:" + this.passwd;
    }
}
