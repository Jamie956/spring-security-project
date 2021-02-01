package com.jamie;

import com.jamie.security.TokenManager;
import com.jamie.utils.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FirstTest {
    @Test
    public void t1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String secret = bCryptPasswordEncoder.encode("22");
        //$2a$10$iwXd/lsYD29v6X6eWpsvvOuBLfZ9Bz4siDJihIqQ59HRvnZGZkSxq
        System.out.println(secret);
        boolean isMatch = bCryptPasswordEncoder.matches("jamie956", secret);
        System.out.println(isMatch);
    }

    @Test
    public void t2() {
        String a = MD5.encrypt("11");
        String b = MD5.encrypt("22");
    }

    @Test
    public void t3() {
        TokenManager tokenManager = new TokenManager();
        String token = tokenManager.createToken("jamie");
        String user = tokenManager.getUser(token);
    }
}
