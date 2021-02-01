package com.jamie.controller;

import com.jamie.exception.CustomException;
import com.jamie.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExceptionController {

    /**
     * 触发 CustomException 异常
     * http://localhost:8080/t1?num=1
     * {"code":400,"message":"num不能为空","data":null}
     *
     * 成功 不触发异常
     * http://localhost:8080/t1?num=5
     * {"code":200,"message":"成功","data":null}
     *
     * 触发 ArithmeticException -> RuntimeException 异常
     * http://localhost:8080/t1?num=0
     * {"code":400,"message":"/ by zero","data":null}
     *
     * @param num
     * @return
     */
    @GetMapping("/t1")
    public Result t1(@RequestParam Integer num) {
        if (num == 1) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return Result.ok().message("成功");
    }

    /**
     * 测试返回集合
     * http://localhost:8080/t2
     * @return
     */
    @GetMapping("t2")
    public Result t2() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("spring");

        return Result.ok().data(list);
    }

    /**
     * 测试返回map
     * http://localhost:8080/t3
     * @return
     */
    @GetMapping("t3")
    public Result t3() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "tim");
        map.put("age", "6");

        return Result.ok().data(map);
    }
}