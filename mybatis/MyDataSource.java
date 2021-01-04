// DataSource 이용한 DB접속 id, pw 복호화

applicationContext.xml

<bean id="dataSource" class="abc.dao.MyDataSource" destroy-method="close">
 <property name="driverClassName" value="com.microsoft.sqlserver.jdbc.SQLServerDriver"/>
 <property name="url" value="jdbc:sqlserver://1.1.1.1:34331;DataBaseName=abc"/>
 <property name="username" value="aaaa122"/>
 <property name="password" value="bbbb11"/>
</bean>

MyDataSource.java

package arc.dao;

public class MyDataSource extends BasicDataSource {
 @override
 public void setUsername(String username) {
  String dec = Enc.dec(username);
  super.setUsername(dec);
 }

 @override
 public void setPassword(String password) {
  String dec = Enc.dec(password);
  super.setPassword(dec);
 }
}
