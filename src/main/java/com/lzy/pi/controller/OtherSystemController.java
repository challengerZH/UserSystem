package com.lzy.pi.controller;

import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.constants.BaseConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/28 10:44
 */

@RestController
@RequestMapping("/api/othersystem")
public class OtherSystemController {

    @RequestMapping("/v1.0/uploadImage")
    public BaseResponse uploadImage() {
        BaseResponse response = new BaseResponse(true, BaseConstants.SUCCESS_CODE);

        return response;
    }

    @RequestMapping("/v1.0/uploadLog")
    public BaseResponse uploadLog() {
        BaseResponse response = new BaseResponse(true, BaseConstants.SUCCESS_CODE);

        return response;
    }

    @RequestMapping("/v1.0/open")
    public void open() {
    }


}
