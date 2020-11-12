package com.appium.test;

import com.appium.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

public class DemoTest {

    @Test
    public void test() throws SQLException {

		QueryRunner queryRunner = new QueryRunner();

		Map<String,Object> maps = queryRunner.query(JdbcUtil.getConnection(),"select i.user_id,i.real_name,w.msg_content,w.send_time from apply_idno_info i LEFT JOIN withdraw_send_msg_info w on i.user_id=w.user_id where real_name='王晓曈' order by w.send_time desc limit 1",new MapHandler());

		System.out.println(maps.toString());

    }
}
