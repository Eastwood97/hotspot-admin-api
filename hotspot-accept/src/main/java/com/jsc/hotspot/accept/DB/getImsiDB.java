package com.jsc.hotspot.accept.DB;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/**
 * @Auther: WW
 * @Date: 2019/11/15 0015 09:03
 * @Description: splite 数据库连接操作
 */
@Component
public class getImsiDB {
    private static Connection connection = null;
    protected static Statement statement = null;
    private static ResultSet resultSet = null;
    private static HashMap<Integer, String> dicorg = new HashMap<Integer, String>();

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:d:/assets/numarea.db");
            System.out.println(connection);
        } catch (Exception e) {
        }
    }

    public static String getAreaString(String imsi) {
        StringBuffer numSB = new StringBuffer();
        GetPhoneNumByIMSI(imsi, numSB);
        String num = numSB.toString();
        return GetNumAreaInfo(Integer.parseInt(num));
    }

    private static int GetPhoneNumByIMSI(String imsi, StringBuffer numSB) {
        String num = "";
        String networkid = imsi.substring(3, 5);
        String mobileid = imsi.substring(5, 10);
        if (networkid.equals("00"))//移动
        {
            String idflag = mobileid.substring(3, 4);
            if (idflag.equals("5")) {
                num = "1350";
            } else if (idflag.equals("6")) {
                num = "1360";
            } else if (idflag.equals("7")) {
                num = "1370";
            } else if (idflag.equals("8")) {
                num = "1380";
            } else if (idflag.equals("9")) {
                num = "1390";
            } else if (idflag.equals("0")) {
                num = "135" + mobileid.substring(4, 5);
            } else if (idflag.equals("1")) {
                num = "136" + mobileid.substring(4, 5);
            } else if (idflag.equals("2")) {
                num = "137" + mobileid.substring(4, 5);
            } else if (idflag.equals("3")) {
                num = "138" + mobileid.substring(4, 5);
            } else if (idflag.equals("4")) {
                num = "139" + mobileid.substring(4, 5);
            }
            num += mobileid.substring(0, 3);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("01"))//联通
        {
            String numid = mobileid.substring(4, 5);
            if (numid.equals("0") || numid.equals("1")) {
                num = "130";
            } else if (numid.equals("9")) {
                num = "131";
            } else if (numid.equals("2")) {
                num = "132";
            } else if (numid.equals("3")) {
                num = "156";
            } else if (numid.equals("4")) {
                num = "155";
            } else if (numid.equals("5")) {
                num = "185";
            } else if (numid.equals("6")) {
                num = "186";
            } else if (numid.equals("7")) {
                num = "145";
            } else {
                return 3;//"不支持该类型IMSI码查询"
            }
            num += mobileid.substring(3, 4) + mobileid.substring(0, 3);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("02"))  //移动
        {
            char numid = mobileid.charAt(0);
            if (numid == '0') {
                num = "134";
            } else if (numid == '1') {
                num = "151";
            } else if (numid == '2') {
                num = "152";
            } else if (numid == '3') {
                num = "150";
            } else if (numid == '7') {
                num = "187";
            } else if (numid == '9') {
                num = "159";
            } else if (numid == '8') {
                num = "158";
            } else {
                return 3;//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("07"))    //157 or 188 存在疑问
        {
            char numid = mobileid.charAt(0);
            if (numid == '7') {
                num = "157";
            } else if (numid == '8') {
                num = "188";
            } else if (numid == '9') {
                num = "147";
            } else {
                return 3;//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            numSB.append(num);
            return 0;
        } else {
            return 2;//运营商代码不支持
        }
    }

    private static String GetNumAreaInfo(int num) {
        if (dicorg.containsKey(num)) {
            return dicorg.get(num);
        } else {
            String msg = GetNumAreaInfo_DB(num);
            dicorg.put(num, msg);
            return msg;
        }
    }

    private static String GetNumAreaInfo_DB(int num) {
        String retstr = "";
        try {
            Statement stat = connection.createStatement();
            // 2.创建表格或视图
            //-- 表：images
            resultSet = stat.executeQuery("SELECT * from numarea where Mobile = " + num);
            while (resultSet.next()) {
                String col1 = resultSet.getString("City");
                retstr =  col1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retstr;
    }
}