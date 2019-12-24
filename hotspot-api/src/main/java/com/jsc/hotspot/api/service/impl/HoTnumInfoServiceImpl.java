package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.HoTnumInfoService;
import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.domain.HotNumInfoExample;
import com.jsc.hotspot.db.entity.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 08:29
 * @Description: 查询删除操作
 */
@Service
@Slf4j
public class HoTnumInfoServiceImpl implements HoTnumInfoService {
    @Autowired
    private HotNumInfoMapper hotNumInfoMapper;
    @Autowired
    private HotNumInfoEXTMapper hotNumInfoEXTMapper;
    @Override
    public PageResult findHotNumInfo(int page, int rows, HotNumInfo hotNumInfoDAO) {
        PageHelper.startPage(page, rows);
        HotNumInfoExample hotNumInfoDAOExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoDAOExample.createCriteria();
//        判断数据
        if (hotNumInfoDAO.getImsi() != null && hotNumInfoDAO.getImsi().length() > 0) {
            criteria.andImsiLike("%" + hotNumInfoDAO.getImsi() + "%");
        }
        if (hotNumInfoDAO.getImei() != null && hotNumInfoDAO.getImei().length() > 0) {
            criteria.andImeiLike("%" + hotNumInfoDAO.getImei() + "%");
        }
        if (hotNumInfoDAO.getIsdn() != null && hotNumInfoDAO.getIsdn().length() > 0) {
            criteria.andIsdnLike("%" + hotNumInfoDAO.getIsdn() + "%");
        }
        if (hotNumInfoDAO.getMode() != null ) {
            criteria.andModeEqualTo(hotNumInfoDAO.getMode());
        }
        if (hotNumInfoDAO.getCaptureTime() != null) {
            criteria.andCaptureTimeBetween( LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MIN), LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MAX));
        }
        if (hotNumInfoDAO.getDevId() != null) {
            criteria.andDevIdEqualTo(hotNumInfoDAO.getDevId());
        }
        if (hotNumInfoDAO.getTargetId() != null) {
            criteria.andTargetIdEqualTo(hotNumInfoDAO.getTargetId());
        }
        if (hotNumInfoDAO.getId() != null) {
            criteria.andIdEqualTo(hotNumInfoDAO.getId());
        }
        Page<HotNumInfo> pages = (Page<HotNumInfo>) hotNumInfoMapper.selectByExample(hotNumInfoDAOExample);
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void deleteHotNumInfo(String ids) {
        List<String> strings = JSONArray.parseArray(ids, String.class);
        for (String id : strings) {
            hotNumInfoMapper.deleteByPrimaryKey(Long.decode(id));
        }
    }

    @Override
    public Long getHoTnumInfoNum() {
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        long count = hotNumInfoMapper.countByExample(hotNumInfoExample);
        return count;
    }

    @Override
    public List getHoTnumInfoDateNum() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            c.add(Calendar.DATE, -1);
            Date d = c.getTime();
            dateList.add(d);
        }
        List<Map> maps1 = hotNumInfoEXTMapper.selectCount(dateList);
        return maps1;
    }

    @Override
    public Long getTodayHoTnumInfoNum() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        criteria.andCaptureTimeGreaterThan(localDateTime);
        long count = hotNumInfoMapper.countByExample(hotNumInfoExample);
        return count;
    }

    @Override
    public void insertHoTnumInfoNum(HotNumInfo hotNumInfoDAO) {
        hotNumInfoMapper.insert(hotNumInfoDAO);
    }

    @Override
    public List getGuiShuDiList() {
        List<Map> list = hotNumInfoEXTMapper.selectGuiShuDiList();
        return list;
    }

    @Override
    public List<Map> getTraffic(Integer devId, String[] createTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        Date startTime = formatter.parse(createTime[0], pos);
        Date endTime = formatter.parse(createTime[1], pos1);
        Date[] date = new Date[2];
        date[0]=startTime;
        date[1]=endTime;
        Date[] objects = getDate(date);
        List<Map> list = hotNumInfoEXTMapper.selectTraffic(devId, startTime,endTime);
        return getList(objects.length - 1, objects, list, devId);
    }

    @Override
    public Object getDifferentCountries(Integer devId, String[] createTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        Date startTime = formatter.parse(createTime[0], pos);
        Date endTime = formatter.parse(createTime[1], pos1);
        Date[] date = new Date[2];
        date[0]=startTime;
        date[1]=endTime;
        Date[] objects = getDate(date);
        List<Map> guoBieList = selectGuoBieCount(devId, date);//各省
        List GuojiCountList = selectGuojiCount(devId, date);//国内外数量
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        List<Map> GuoWaiGeGuoCount = hotNumInfoEXTMapper.selectGuoWaiGeGuoCount(devId,  startTime,endTime);
        Map MyGuoCount = hotNumInfoEXTMapper.selectMyGeGuoCount(devId, startTime,endTime);
        objectObjectHashMap.put("guoBieList", guoBieList);
        objectObjectHashMap.put("GuojiCountList", GuojiCountList);
        objectObjectHashMap.put("GuoWaiGeGuoCount", GuoWaiGeGuoCount);
        objectObjectHashMap.put("MyGuoCount", MyGuoCount);
        return objectObjectHashMap;
    }

    public List<Map> selectGuoBieCount(Integer devId, Date[] createTime) {
        Date[] objects = getDate(createTime);
        List<Map> list = hotNumInfoEXTMapper.selectGuoBieCount(devId, createTime[0],createTime[1]);
        return getGuoBieList(objects.length - 1, objects, list, devId);
    }
    public List selectGuojiCount(Integer devId, Date[] createTime) {
        Date[] objects = getDate(createTime);
        Map myCountryCount = hotNumInfoEXTMapper.selectGuojiCount(devId, createTime[0],createTime[1]);
        Map BroadCount = hotNumInfoEXTMapper.selectGuojiCounts(devId, createTime[0],createTime[1]);
        ArrayList<Object> objects1 = new ArrayList<>();
        objects1.add(myCountryCount);
        objects1.add(BroadCount);
        return objects1;
    }
    public Date[] getDate(Date[] createTime) {
        Calendar calendar = new GregorianCalendar();
        Integer nd = 1000 * 24 * 60 * 60;
        Date startDate = createTime[0];
        Date endDate = createTime[1];
        Integer index = Math.toIntExact((endDate.getTime() - startDate.getTime()) / nd);
        Date[] objects = new Date[index + 1];
        for (int i = 0; i <= index; i++) {
            calendar.setTime(startDate);
            calendar.add(calendar.DATE, +i);
            objects[i] = calendar.getTime();
        }
        return objects;
    }

    public List<Map> getList(Integer index, Date[] objects, List<Map> list, Integer devId) {
        List<Map> lists = new ArrayList<>();
        Boolean she;
        for (int i = 0; i <= index; i++) {
            she = false;
            for (Map time : list) {
                she = false;
                Date createTime1 = (Date) time.get("create_time");
                if (differentDays(objects[i], createTime1) == 0) {
//                    此处添加数组
                    Map map = new HashMap();
                    map.put("devId", time.get("dev_id"));
                    map.put("area", time.get("attribution"));
                    map.put("createTime", time.get("create_time"));
                    map.put("num", time.get("num"));
                    map.put("imsi", time.get("imsi"));
                    lists.add(map);
                    she = true;
                    break;
                }
            }
            if (she == false) {
                //                    此处添加数组
                Map map = new HashMap();
                map.put("devId", devId);
                map.put("area", "");
                map.put("createTime", objects[i]);
                map.put("num", 0);
                map.put("imsi", "");
                lists.add(map);
            }
        }
        return lists;
    }

    public int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            return day2 - day1;
        }
    }
    public List<Map> getGuoBieList(Integer index, Date[] objects, List<Map> list, Integer devId) {
        List<Map> lists = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            for (Map time : list) {
                Date createTime1 = (Date) time.get("create_time");
                if (differentDays(objects[i], createTime1) == 0) {
//                    此处添加数组
                    Map map = new HashMap();
                    map.put("devId", time.get("dev_id"));
                    map.put("area", time.get("attribution"));
                    map.put("num", time.get("num"));
                    lists.add(map);
                }
            }
        }
        return lists;
    }
}