package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSONArray;
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

    public List<Map> PortList(int page, int rows, HotNumInfo hotNumInfoDAO) {//口岸
        String imsi = "";
        String imei = "";
        String isdn = "";
        Long dev_id = null;
        LocalDateTime of = null;
        LocalDateTime of1 = null;
        LocalDateTime capture_time = null;
//        判断数据
        if (hotNumInfoDAO.getImsi() != null && hotNumInfoDAO.getImsi().length() > 0) {
            imsi = hotNumInfoDAO.getImsi();
        }
        if (hotNumInfoDAO.getImei() != null && hotNumInfoDAO.getImei().length() > 0) {
            imei = hotNumInfoDAO.getImei();
        }
        if (hotNumInfoDAO.getIsdn() != null && hotNumInfoDAO.getIsdn().length() > 0) {
            isdn = hotNumInfoDAO.getIsdn();
        }
        if (hotNumInfoDAO.getMode() != null) {
            dev_id = hotNumInfoDAO.getDevId();
        }
        if (hotNumInfoDAO.getCaptureTime() != null) {
            capture_time = hotNumInfoDAO.getCaptureTime();
            of = LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MIN);
            of1 = LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MAX);
        }
        if (page == 1) {
            page = 0;
        } else {
            page = rows * (page - 1);
        }

        List<Map> maps = hotNumInfoEXTMapper.selectHotNumInfoList(dev_id, imsi, imei, isdn, capture_time, page, rows, of, of1);
        return maps;
    }

    @Override
    public PageResult findHotNumInfo(int groupId, int page, int rows, HotNumInfo hotNumInfoDAO) {
        HotNumInfoExample hotNumInfoDAOExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoDAOExample.createCriteria();
//        判断数据
        if (hotNumInfoDAO.getImsi() != null && hotNumInfoDAO.getImsi().length() > 0) {
            criteria.andImsiLike(hotNumInfoDAO.getImsi() + "%");
        }
        if (hotNumInfoDAO.getImei() != null && hotNumInfoDAO.getImei().length() > 0) {
            criteria.andImeiLike(hotNumInfoDAO.getImei() + "%");
        }
        if (hotNumInfoDAO.getIsdn() != null && hotNumInfoDAO.getIsdn().length() > 0) {
            criteria.andIsdnLike(hotNumInfoDAO.getIsdn() + "%");
        }
        if (hotNumInfoDAO.getMode() != null) {
            criteria.andModeEqualTo(hotNumInfoDAO.getMode());
        }
        if (hotNumInfoDAO.getCaptureTime() != null) {
            LocalDateTime of = LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MIN);
            LocalDateTime of1 = LocalDateTime.of(hotNumInfoDAO.getCaptureTime().toLocalDate(), LocalTime.MAX);
            criteria.andCaptureTimeBetween(of, of1);
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
        //此处groupId是判断是口岸还是大桥
//        if (groupId == 0) {//口岸
        List<Map> portPageList = PortList(page, rows, hotNumInfoDAO);
        return new PageResult(hotNumInfoMapper.countByExample(hotNumInfoDAOExample), portPageList);
    }

    @Override
    public void deleteHotNumInfo(String ids) {
        List<String> strings = JSONArray.parseArray(ids, String.class);
        for (String id : strings) {
            hotNumInfoMapper.deleteByPrimaryKey(Long.decode(id));
        }
    }

    //取号总量
    @Override
    public Long getHoTnumInfoNum() {
        long count = hotNumInfoEXTMapper.selectDistCount();
        return count;
    }

    /**
     * 功能描述: 提取将当天时间改变为从00:00:00开始
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/12/30 0030 8:33
     */
//    public Date changeDate(Date createTimes) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String createTime = format.format(createTimes);
//        Date parse = null;
//        try {
//            parse = dateFormat.parse(createTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return parse;
//    }

    /**
     * 功能描述: 获取15天公共接口
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/12/3 0003 16:25
     */
    public Date returnDate(Integer index) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -index);
        Date returnDate = c.getTime();
        return returnDate;
    }

    //获取15天的取号数量及其对应15天
    @Override
    public List getHoTnumInfoDateNum() {
        List<Map> maps1 = hotNumInfoEXTMapper.selectCount(returnDate(15), returnDate(0));
        return maps1;
    }

    //今日取号数量
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

    //分析成图的所有时间（从开头到结束）
    public Date[] getYestDayIndex(String[] createTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        Date startTime = formatter.parse(createTime[0], pos);
        Date endTime = formatter.parse(createTime[1], pos1);
        Date[] date = new Date[2];
        date[0] = startTime;
        date[1] = endTime;
        Date[] dateList = getDate(date);
        return dateList;
    }

    //人流查询
    @Override
    public List<Map> getTraffic(Integer devId, String[] createTime) {
        Date[] yestDayList = getYestDayIndex(createTime);
        int index = yestDayList.length - 1;
        List<Map> list = hotNumInfoEXTMapper.selectTraffic(devId, yestDayList[0], yestDayList[index]);
        return getList(yestDayList.length - 1, yestDayList, list, devId);
    }

    @Override
    public Object getDifferentCountries(Integer devId, String[] createTime) {
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        Date startTime = formatter.parse(createTime[0], pos);
        Date endTime = formatter.parse(createTime[1], pos1);
        Date[] date = new Date[2];
        date[0] = startTime;
        date[1] = endTime;
        List<Map> guoBieList = selectGuoBieCount(devId, date);//各省
        List<Map> GuojiCountList = selectGuojiCount(devId, date);//国内外数量
        List<Map> GuoWaiGeGuoCount = hotNumInfoEXTMapper.selectGuoWaiGeGuoCount(devId, startTime, endTime);
        objectObjectHashMap.put("guoBieList", guoBieList);
        objectObjectHashMap.put("GuojiCountList", GuojiCountList);
        objectObjectHashMap.put("GuoWaiGeGuoCount", GuoWaiGeGuoCount);
        objectObjectHashMap.put("MyGuoCount", GuojiCountList.get(0));
        return objectObjectHashMap;
    }

//    public List<HotNumInfo> selectbansuiList(String imsi) {
//        HotNumInfo hotNumInfo = new HotNumInfo();
//        HotNumInfoExample hotNumInfoDAOExample = new HotNumInfoExample();
//        HotNumInfoExample.Criteria criteria = hotNumInfoDAOExample.createCriteria();
////        判断数据
//        criteria.andImsiEqualTo(imsi);
//        hotNumInfo.setImsi(imsi);
//        List<HotNumInfo> hotNumInfoList = hotNumInfoMapper.selectByExample(hotNumInfoDAOExample);
//        return hotNumInfoList;
//    }

//    @Override
//    public PageResult selecttongxingList(Integer currentPage, Integer pageSize, Integer createTime, String imsi) {
//        List<Map<String, Object>> ListOne = new ArrayList<>();
//        List<HotNumInfo> dataMessages = this.selectbansuiList(imsi);
//        List<List<Map>> list = new ArrayList<>();
//        for (HotNumInfo message : dataMessages) {
//            List<Map> dataMessages1 = hotNumInfoEXTMapper.selectbansuiLists(message.getId(),
//                    message.getDevId(), message.getImsi(), createTime);
//            list.add(dataMessages1);
//        }
//        for (int i = 0; i < list.size(); i++) {//循环大的
//            List<Map> dataMessages1 = list.get(i);//获取第二个往后
//            for (int k = 0; k < dataMessages1.size(); k++) {
//                Integer id = (Integer) dataMessages1.get(k).get("id");
//                Integer devId = (Integer) dataMessages1.get(k).get("dev_Id");
//                String imsi1 = (String) dataMessages1.get(k).get("imsi");
//                String imei = (String) dataMessages1.get(k).get("imei");
//                String area = (String) dataMessages1.get(k).get("attribution");
//                String shengshi = (String) dataMessages1.get(k).get("attribution");
//                String location = (String) dataMessages1.get(k).get("attribution");
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", id);
//                map.put("devId", devId);
//                map.put("imsi", imsi1);
//                map.put("imei", imei);
//                map.put("area", area);
//                map.put("shengshi", shengshi);
//                map.put("location", location);
//                map.put("num", 0);
//                ListOne.add(map);
//            }
//        }
//        List<Map<String, Object>> maps = this.removeRepeatMapByKey(ListOne);
//        List<HotNumInfoObject> Lists = new ArrayList<>();
//        for (Map<String, Object> map : maps) {
//            Integer id = (Integer) map.get("id");
//            Integer devId = (Integer) map.get("devId");
//            String imsi1 = (String) map.get("imsi");
//            String imei = (String) map.get("imei");
//            String area = (String) map.get("area");
//            String shengshi = (String) map.get("attribution");
//            String location = (String) map.get("location");
//            Integer num = (Integer) map.get("num");
//            Lists.add(new HotNumInfoObject(id, devId, imsi1, imei, area, shengshi, location, num));
//
//        }
//        List<HotNumInfoObject> sort = this.sort(Lists);
//        ListSub<HotNumInfoObject> objectListSub = new ListSub<>(currentPage, pageSize, sort);
//        return new PageResult(objectListSub.getTotal(), objectListSub.getList());
//    }
@Override
public Map<String, Integer> getNumInfoByDay() {
    return null;
}

    @Override
    public Map<String, Integer> getNumInfoByMonth() {
        //return hotNumInfoEXTMapper.selectNumInfoByMonth();
        return null;
    }
    /**
     * 功能描述: 对list中map进行排序
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/12/17 0017 7:59
     */
//    public List<HotNumInfoObject> sort(List<HotNumInfoObject> list) {
//        if (list != null && list.size() > 1) {
//            Collections.sort(list, new Comparator<HotNumInfoObject>() {
//                @Override
//                public int compare(HotNumInfoObject o1, HotNumInfoObject o2) {
//                    Integer num1 = o1.getNum();
//                    Integer num2 = o2.getNum();
//                    return num2.compareTo(num1);
//                }
//            });
//        }
//        return list;
//    }

    /**
     * 功能描述: 根据map中的某个key 去除List中重复的map
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/12/17 0017 8:48
     */
//    public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list) {
//
//        List<Integer> integerList = new ArrayList<>();
//        for (int i = 0; i < list.size() - 1; i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                Map<String, Object> map = list.get(i);
//                String imsi1 = (String) map.get("imsi");
//                Map<String, Object> maps = list.get(j);
//                String imsi2 = (String) maps.get("imsi");
//                if (imsi1.equals(imsi2)) {
//                    integerList.add(j);
//                    Integer num = (Integer) map.get("num");
//                    map.put("num", num + 1);
//                    list.remove(j);
//                }
//            }
//        }
//
//        return list;
//    }

    public List<Map> selectGuoBieCount(Integer devId, Date[] createTime) {
        Date[] objects = getDate(createTime);
        List<Map> list = hotNumInfoEXTMapper.selectGuoBieCount(devId, createTime[0], createTime[1]);
        return list;
    }

    public List<Map> selectGuojiCount(Integer devId, Date[] createTime) {
        Date[] objects = getDate(createTime);
        Map myCountryCount = hotNumInfoEXTMapper.selectGuojiCount(devId, createTime[0], createTime[1]);
        Map BroadCount = hotNumInfoEXTMapper.selectGuojiCounts(devId, createTime[0], createTime[1]);
        ArrayList<Map> objects1 = new ArrayList<>();
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
                Date createTime1 = (Date) time.get("create_time1");
                if (differentDays(objects[i], createTime1) == 0) {
                    Map map = new HashMap();
                    map.put("createTime", time.get("create_time1"));
                    map.put("num", time.get("num"));
                    lists.add(map);
                    she = true;
                    break;
                }
            }
            if (she == false) {
                Map map = new HashMap();
                map.put("createTime", objects[i]);
                map.put("num", 0);
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

//    public List<Map> getGuoBieList(Integer index, Date[] objects, List<Map> list, Integer devId) {
//        List<Map> lists = new ArrayList<>();
//        for (int i = 0; i <= index; i++) {
//            for (Map time : list) {
//                Map map = new HashMap();
//                map.put("devId", time.get("dev_id"));
//                map.put("area", time.get("attribution"));
//                map.put("num", time.get("num"));
//                lists.add(map);
//            }
//        }
//        return lists;
//    }
    public List<Map> getGuoBieList(Integer index, Date[] objects, List<Map> list, Integer devId) {
        List<Map> lists = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            for (Map time : list) {
                    Map map = new HashMap();
                    map.put("devId", time.get("dev_id"));
                    map.put("area", time.get("attribution"));
                    map.put("num", time.get("num"));
                    lists.add(map);
            }
        }
        return lists;
    }


    @Override
    public Long countImsiCatNums() {
        return new Long(hotNumInfoEXTMapper.countDistinctByIMSI());
    }

    @Override
    public Long currentImsiNums() {
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime currentMin = LocalDateTime.MIN;
        LocalDateTime currentMax = localDateTime.MAX;
        criteria.andCaptureTimeBetween(currentMin, currentMax);
        return hotNumInfoMapper.countByExample(hotNumInfoExample);
    }

}