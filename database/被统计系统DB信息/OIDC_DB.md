# OIDC_DB

## 详细配置

``` xml
	<bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
		<property name="driverClassName" value="oracle.jdbc.OracleDriver" />
		<property name="jdbcUrl" value="jdbc:oracle:thin:@10.244.231.91:1521:xtwl" />
		<property name="username" value="openidcon" />
		<property name="password" value="security.n7Rp8j" />
	</bean>
```