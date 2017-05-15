package cn.cloudself.components.controller;

import cn.cloudself.exception.http.RequestBadException;
import cn.cloudself.exception.http.RequestNotFindException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@RestController
public class ErrorController {

    @RequestMapping("/error/404/")
    public ResponseEntity<String> error404() throws Exception {
        throw new RequestNotFindException();
    }

}
