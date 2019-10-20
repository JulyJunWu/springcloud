package com.ws.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jun
 * data  2019-10-20 19:35
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    /**
     * 姓名
     */
    private String name = "ws";

    /**
     * 年龄
     */
    private int age = 16;

    /**
     * 爱好
     */
    private String hobby = "(✺ω✺)";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
