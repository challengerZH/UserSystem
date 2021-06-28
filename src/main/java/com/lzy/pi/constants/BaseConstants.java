package com.lzy.pi.constants;


public final class BaseConstants {

    private BaseConstants() {

    }
    //成功返回编码
    public static final  String SUCCESS_CODE = "000000";
    public static final  String FAULT_CODE = "999999";
    public static final  String DEFAULT_USER = "999999";


    /**
     * @description:
     * @author: zhouhua
     * @date: 2021-06-28 15:22
     */
    public enum LabelType {
        PLATFORM_LEVEL("1", "平台级"),
        STORE_LEVEL("2", "门店级"),
        ;

        LabelType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static String getValueByKey(String key) {
            for (LabelType type : LabelType.values()) {
                if (type.getKey().equals(key)) {
                    return type.getValue();
                }
            }
            return null;
        }
    }

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
