package com.lzy.pi.constants;


public final class BaseConstants {

    private BaseConstants() {

    }
    //成功返回编码
    public static final  String SUCCESS_CODE = "000000";
    public static final  String FAULT_CODE = "999999";
    public static final  String DEFAULT_USER = "999999";


    /**
     * 日志类型：1：系统登录操作日志， 2：门店刷脸登录日志， 3：告警日志
     */
    public static final class LogType {
        private LogType() {
        }

        public static final String OPERATION_LOG = "1";

        public static final String LOGIN_LOG = "2";

        public static final String SYSTEM_LOG = "3";

    }


}
