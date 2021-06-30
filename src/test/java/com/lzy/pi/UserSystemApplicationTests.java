package com.lzy.pi;

import com.lzy.pi.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
class UserSystemApplicationTests {
    @Autowired
    private StaffService staffService;

    @Test
    void contextLoads() {

//        staffService.check();
    }

}
