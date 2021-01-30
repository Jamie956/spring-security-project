package com.jamie;

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
}
