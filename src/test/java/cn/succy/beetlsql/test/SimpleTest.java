package cn.succy.beetlsql.test;

import cn.succy.beetlsql.bean.User;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author Succy
 * @date 2017/4/21 17:36
 */
public class SimpleTest {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/beetlsql?useUnicode=true&characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PWD = "wsgsx2914";
    private SQLManager manager;
    @Before
    public void setUp() {
        ConnectionSource source = ConnectionSourceHelper.getSimple(DRIVER, URL, USERNAME, PWD);
        DBStyle style = new MySqlStyle();
        SQLLoader loader = new ClasspathLoader("/sql");
        UnderlinedNameConversion unc = new UnderlinedNameConversion();
        manager = new SQLManager(style, loader, source, unc, new Interceptor[]{new DebugInterceptor()});
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setAge(22);
        user.setName("succy");
        user.setUserName("shuxi");
        user.setCreateDate(new Date());

        manager.insert(user);
    }

    @Test
    public void testFindUserById() {
        int id = 3;
        User user = manager.unique(User.class, id);
        System.out.println(user);
    }

    @Test
    public void testUpdateTmplById() {
        User user = new User();
        user.setId(3);
        user.setAge(32);
        user.setName("大屌丝");
        int count = manager.updateTemplateById(user);
        System.out.println(String.format("受影响行数: %d", count));
    }

    @Test
    public void testFindByTmpl() {
        User user = new User();
        user.setName("大屌丝");

        List<User> list = manager.template(user);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectFromSqlFile() {
        User user = new User();
        user.setId(3);
        user.setName("大屌丝");

        List<User> list = manager.select("user.select", User.class, user);
        list.forEach(System.out::println);
    }
}
