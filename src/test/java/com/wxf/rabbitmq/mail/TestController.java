package com.wxf.rabbitmq.mail;

import com.wxf.rabbitmq.mail.utils.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

    @Autowired
    JedisUtil jedisUtil;

    @Test
    public void test() {
        boolean b = jedisUtil.tryGetDistributedLock("mail", "mail", 60 * 1000);
        System.out.println(b);

        boolean b1 = jedisUtil.releaseDistributedLock("mail", "mail");
        System.out.println(b1);
    }

    @Test
    public void testJdbc() throws ClassNotFoundException, SQLException {

        // JDBC 驱动名及数据库URL
         final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
         final String DB_URL = "jdbc:mysql://10.241.1.13:33307/saas_medical_trash_history";

        // 数据库的用户名与密码，需要根据自己的设置
         final String USER = "wandadba";
         final String PASS = "wandadba321";
        // 注册JDBC驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // 执行查询
        long l = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        for(int i = 1 ; i <= 30; i ++) {
            String date = "2020-01-"+i;
            String sql = "SELECT event_ts,cur_store FROM t_storelog where event_ts < '"+date+"' and org_id  ='1401010000015882'  order by event_ts desc LIMIT 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Date date1 = rs.getDate(1);
                BigDecimal bigDecimal = rs.getBigDecimal(2);
                System.out.println("i:"+i+" "+date1+bigDecimal);
            }
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        conn.close();
        long l1 = System.currentTimeMillis();
        System.out.println("用时总计："+(l1-l));
    }

    @Test
    public void testJdbc2() throws ClassNotFoundException, SQLException {

        // JDBC 驱动名及数据库URL
         final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
         final String DB_URL = "jdbc:mysql://10.241.83.5:3306/saas_medical_trash_dev";

        // 数据库的用户名与密码，需要根据自己的设置
         final String USER = "yifeidba";
         final String PASS = "T9%xJQnW,Oa";
        // 注册JDBC驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // 执行查询
        long l = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        for(int i = 1 ; i <= 30; i ++) {
            String date = "2020-01-"+i;
            String sql = "SELECT record_time,current_stock_trash FROM organization_stock_log where record_time < '"
                    +date+"' and organization_id  ='1401010000015882'  order by record_time desc LIMIT 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Date date1 = rs.getDate(1);
                BigDecimal bigDecimal = rs.getBigDecimal(2);
                System.out.println("i:"+i+" "+date1+" "+bigDecimal);
            }
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        conn.close();
        long l1 = System.currentTimeMillis();
        System.out.println("用时总计："+(l1-l));
    }
    }
