package com.jsc.hotspot.api.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.LogService;
import com.jsc.hotspot.api.service.HotSpotLogService;
import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.db.dao.AdminMapper;
import com.jsc.hotspot.db.dao.HotFrontDeviceMapper;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.dao.RoleMapper;
import com.jsc.hotspot.db.dao.ext.HotTargetFaceEXTMapper;
import com.jsc.hotspot.db.domain.*;
import com.jsc.hotspot.db.entity.TargetFaceResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统切面
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private HotSpotLogService sysLogService;
    @Autowired
    private HotTargetInfoMapper hotTargetInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HotFrontDeviceMapper hotFrontDeviceMapper;
    @Autowired
    private HotTargetFaceEXTMapper hotTargetFaceEXTMapper;

    //定义切点@Pointcut
    @Pointcut("execution(public * com.jsc.hotspot.api.controller..*.*(..))")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void saveSysLogs(JoinPoint joinPoint) {
        //切面
        HotspotLog sysLog = new HotspotLog();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogService myLog = method.getAnnotation(LogService.class);
        if (myLog != null) {
            String value = myLog.value();
            if (!"用户登录".equals(value)) {
                Subject subject = SecurityUtils.getSubject();
                Admin user = (Admin) subject.getPrincipal();
                String username = user.getUsername();
                sysLog.setUsername(username);
                sysLog.setOperation(value);
                //获取请求的类名
                String className = joinPoint.getTarget().getClass().getName();
                sysLog.setMethod(className + "." + method.getName());
                //请求参数
                Object[] args = joinPoint.getArgs();
                String param = judgeContext(value, args) + "\n" + "操作用户：" + username;
                sysLog.setParams(param);
                String ip = subject.getSession().getHost();
                sysLog.setIp(ip);
                LocalDateTime localDateTime = LocalDateTime.now();
                sysLog.setCreatedate(localDateTime);
                sysLogService.insertSysLog(sysLog);
            }

        }
    }

    //切面配置通知
//    @AfterReturning("logPointcut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //切面
        HotspotLog sysLog = new HotspotLog();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogService myLog = method.getAnnotation(LogService.class);
        if (myLog != null) {
            String value = myLog.value();
            if ("用户登录".equals(value)) {
                Subject subject = SecurityUtils.getSubject();
                Admin user = (Admin) subject.getPrincipal();
                String username = user.getUsername();
                sysLog.setUsername(username);
                sysLog.setOperation(value);
                //获取请求的类名
                String className = joinPoint.getTarget().getClass().getName();
                sysLog.setMethod(className + "." + method.getName());
                //请求参数
                Object[] args = joinPoint.getArgs();
                String param = judgeContext(value, args);
                sysLog.setParams(param);
                String ip = subject.getSession().getHost();
                sysLog.setIp(ip);
                LocalDateTime localDateTime = LocalDateTime.now();
                sysLog.setCreatedate(localDateTime);
                sysLogService.insertSysLog(sysLog);
            }
        }
    }

    public String judgeContext(String method, Object[] args) {
        //判断用户登录
        if ("用户登录".equals(method)) {
            //第一步拆数据args
            String str = args[0].toString();
            HashMap<String, String> map = JSON.parseObject(str, HashMap.class);
            String username = map.get("username");
            String password = map.get("password");
            return "用户名：" + username + "\n" + "密码：" + password;
        }
        if ("删除黑名单".equals(method)) {
            String str = args[0].toString();
            long id = Long.parseLong(str);
            HotTargetInfo hotTargetInfo = hotTargetInfoMapper.selectByPrimaryKey(id);
            if (hotTargetInfo != null) {
                String targetName = hotTargetInfo.getTargetName();
                String imsi = hotTargetInfo.getImsi();
                String caseName = hotTargetInfo.getCaseName();
                Long operatorId = hotTargetInfo.getOperatorId();
                return "名称：" + targetName + "\n" + "imsi：" + imsi + "\n" + "案件名：" + caseName;
            } else {
                return "";
            }

        }
        if ("添加黑名单".equals(method)) {
            //第一步拆数据args
            HotTargetInfo str = (HotTargetInfo) args[0];
            String targetName = (str.getTargetName()).toString();
            String imsi = (str.getImsi()).toString();
            String caseName = (str.getCaseName()).toString();
            return "目标名称：" + targetName + "\n" + "imsi:" + imsi + "\n" + "案件名：" + caseName;
        }
        if ("修改黑名单".equals(method)) {
            //第一步拆数据args
            HotTargetInfo str = (HotTargetInfo) args[0];
            String targetId = (str.getTargetId()).toString();
            String targetName = (str.getTargetName()).toString();
            String imsi = (str.getImsi()).toString();
            String operatorId = (str.getOperatorId()).toString();
            String caseName = (str.getCaseName()).toString();
            String createTime = (str.getCreateTime()).toString();
            return "目标ID：" + targetId + "\n" + "目标名称：" + targetName + "\n" + "imsi:" + imsi + "\n" + "案件名：" + caseName;
        }
        if ("添加设备".equals(method)) {
            //第一步拆数据args
            HotFrontDevice str = (HotFrontDevice) args[0];
            String devName = (str.getDevName()).toString();
            String devId = (str.getDevId()).toString();
            String devType = (str.getDevType()).toString();
            String ipAddr = (str.getIpAddr()).toString();
            String port = (str.getPort()).toString();
            String createTime = (str.getCreateTime()).toString();
            return "设备ID：" + devId + "\n" + "设备名称：" + devName + "\n" + "设备类型:" + devType + "\n" + "设备端口：" + port + "\n" + "设备IP：" + ipAddr;
        }
        if ("修改设备".equals(method)) {
            //第一步拆数据args
            HotFrontDevice str = (HotFrontDevice) args[0];
            String devName = (str.getDevName()).toString();
            String devId = (str.getDevId()).toString();
            String devType = (str.getDevType()).toString();
            String ipAddr = (str.getIpAddr()).toString();
            String port = (str.getPort()).toString();
            String createTime = (str.getCreateTime()).toString();
            return "设备ID：" + devId + "\n" + "设备名称：" + devName + "\n" + "设备类型:" + devType + "\n" + "设备端口：" + port + "\n" + "设备IP：" + ipAddr;
        }
        if ("删除设备".equals(method)) {
            //第一步拆数据args
            String str = args[0].toString();
            long id = Long.parseLong(str);
            HotFrontDevice hotFrontDevice = hotFrontDeviceMapper.selectByPrimaryKey(id);
            String devName = (hotFrontDevice.getDevName()).toString();
            String devId = (hotFrontDevice.getDevId()).toString();
            String devType = (hotFrontDevice.getDevType()).toString();
            String ipAddr = (hotFrontDevice.getIpAddr()).toString();
            String port = (hotFrontDevice.getPort()).toString();
            return "设备ID：" + devId + "\n" + "设备名称：" + devName + "\n" + "设备类型:" + devType + "\n" + "设备端口：" + port + "\n" + "设备IP：" + ipAddr;
        }
        if ("添加用户".equals(method)) {
            //第一步拆数据args
            Admin str = (Admin) args[0];
            String username = (str.getUsername());
            Long[] roleIds = str.getRoleIds();
            Long roleId = roleIds[0] + 1;
            Role role = roleMapper.selectByPrimaryKey(roleId);
            return "用户名：" + username + "\n" + "权限级别:" + role.getName();
        }
        if ("修改用户".equals(method)) {
            //第一步拆数据args
            Admin str = (Admin) args[0];
            String username = (str.getUsername());
            Long[] roleIds = str.getRoleIds();
            Long roleId = roleIds[0] + 1;
            Role role = roleMapper.selectByPrimaryKey(roleId);
            return "用户名：" + username + "\n" + "权限级别:" + role.getName();
        }
        if ("删除用户".equals(method)) {
            //第一步拆数据args
            Admin str = (Admin) args[0];
            String username = (str.getUsername());
            return "用户名：" + username;
        }
        if ("添加人脸".equals(method)) {
            //第一步拆数据args
            TargetFace str = (TargetFace) args[0];
            String targetName = str.getTargetName();
            String desc = str.getDesc();
            return "目标名称：" + targetName + "\n" + "描述:" + desc;
        }
        if ("修改人脸".equals(method)) {
            //第一步拆数据args
            TargetFace str = (TargetFace) args[0];
            String targetName = str.getTargetName();
            String desc = str.getDesc();
            return "目标名称：" + targetName + "\n" + "描述:" + desc;
        }
        if ("删除人脸".equals(method)) {
            //第一步拆数据args
            String str = args[0].toString();
            long id = Long.parseLong(str);
            TargetFaceResult targetFaceResult = hotTargetFaceEXTMapper.selectById(id);
            String targetName = targetFaceResult.getTargetName();
            String desc = targetFaceResult.getDesc();
            return "目标名称：" + targetName + "\n" + "描述:" + desc;
        }
        return "";
    }
}
