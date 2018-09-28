package com.chai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: minquan.chai@hand-china.com
 * @Modificd By:
 * @Date: Created in chai on 2018/9/28.
 */

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String say() {
        return "Hello World";
    }
}
