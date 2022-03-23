package com.mordor.app;

import lombok.Getter;
import lombok.Setter;

public class HttpRes {
    String content;

    HttpRes(String content) {
        this.content = content;
    }
}
