// DB PW 복호화하여 접속

public class SqlSessionUtil {

 private static SqlSessionFactory sqlSessionFactory;

 private SqlSessionUtil() {
  ;
 }

 static {
  Reader reader = null;
  try {
   reader = Resources.getResourceAsReader("abc/db/mybatis-config.xml");
  } catch (Exception e) {
   e.printStackTrace();
  }

  Properties props = new Properties();
  Reader readerProp = null;
  try {
   readerProp = Resources.getResourceAsReader("abc/db/jdbc.properties");
   props.load(readerProp);
  } catch (Exception e);
 }

 String id = props.getProperty("jdbc.username");
 props.setProperty("jdbc.username", Enc.dec(id));
 String pw = props.getProperty("jdbc.password");
 props.setProperty("jdbc.password", Enc.dec(pw));
 sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, props);
 }

 public static SqlSessionFactory getSqlSessionFactory() {
  return sqlSessionFactory;
 }
}
