# 金流系统DB

## DB信息

```

cashflow/cashflow.2018
10.244.231.91/xtwl


```

---数据查询

```

用户数据：DB：am/987yrfwet2n.am；10.244.231.91/xtwl
SELECT U.*
  FROM SYS_MENU M, RESCOURCE_FIXED F, ROLE_RESOURCE R, SYS_USER_RELATION UR, SYS_USERS U
 WHERE M.ID = F.CONTENT
   AND F.ID = R.RESOURCE_ID
   AND R.ROLE_ID = UR.ROLES
   AND UR.USERID = U.USERID
   AND M.ID = 'A11B902ADB06CCE8E050F40A5BE72308'

SELECT U."USERID",U."USERALIAS",U."USERNAME",U."ISADMIN", M.ID SYSTEM_ID, M.MENU
  FROM SYS_MENU M, RESCOURCE_FIXED F, ROLE_RESOURCE R, SYS_USER_RELATION UR, SYS_USERS U
 WHERE M.ID = F.CONTENT
   AND F.ID = R.RESOURCE_ID
   AND R.ROLE_ID = UR.ROLES
   AND UR.USERID = U.USERID
   AND M.STATUS = '1'
   AND F.STATUS = '1'
   AND R.STATUS = '1'
   AND UR.STATUS = '1'
   --AND M.ID = 'A11B902ADB06CCE8E050F40A5BE72308'
 
 
登录日志：cashflow/cashflow.2018；10.244.231.91/xtwl
SELECT * FROM CF_SYS_LOGIN_LOG T

```

