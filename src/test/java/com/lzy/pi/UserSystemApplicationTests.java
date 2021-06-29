package com.lzy.pi;

import com.lzy.pi.service.StaffService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserSystemApplicationTests {
    @Autowired
    private StaffService staffService;

    @Test
    void contextLoads() {

//        staffService.check();
    }

}
