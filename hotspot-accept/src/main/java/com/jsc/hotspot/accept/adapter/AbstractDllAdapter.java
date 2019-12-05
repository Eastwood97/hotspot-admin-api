package com.jsc.hotspot.accept.adapter;

/**
 * @author huixing
 * @description 抽象的dll功能接口
 * @date 2019/12/3
 */
public interface AbstractDllAdapter {

    void initdll();

    void exit();

    void register();

    void Logout();

    void SetupAlarmChan();
}
