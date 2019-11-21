package com.jsc.hotspot.accept.consts;

/**
 * @author huixing
 * @description 请求路径
 * @date 2019/11/17
 */
public class EasyDrawinUrl {

    // 统计 - 获取推流列表
    public static String GET_PUSHERS = "/api/v1/pushers";

    // 统计 - 获取拉流列表
    public static String GET_PLAYERS = "/api/v1/players";

    // 开启拉转推
    public static String START_GET_TO_PUSHER = "/api/v1/stream/start";

    // 流管理 - 停止推流
    public static String STOP_PUSHER = "/api/v1/stream/stop";

    // 录像 - 获取所有录像文件夹
    public static String GET_ALL_RECORD_FOLDERS = "/api/v1/record/folders";

    // 录像 - 获取所有录像文件夹
    public static String GET_ALL_RECORD_FILE = "/api/v1/record/files";

    // 系统 - 登录
    public static String LOGIN = "/api/v1/login";

    // 系统 - 登出
    public static String LOGOUT = "/api/v1/logout";

    // 系统 - 获取平台运行信息
    public static String GET_PLANTFROM_INFO = "/api/v1/getserverinfo";

    // 系统 - 获取当前登录用户信息
    public static String GET_USER_INFO = "/api/v1/userInfo";

    // 系统 - 重启服务
    public static String RESTART_INFO = "/api/v1/restart";

}
