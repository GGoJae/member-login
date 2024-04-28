package com.example.java8.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
@Slf4j
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        log.debug("[{}] [{}] {}", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
    }

    @PreDestroy
    public void close() {

    }
}
