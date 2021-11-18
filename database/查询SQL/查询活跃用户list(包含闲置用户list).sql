-- 查询某系统某天的活跃用户
select t.emp_id
  from ACTIVE_USER t, REGISTER_USER x
 where t.emp_id = x.emp_id
   and t.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and x.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and trunc(t.active_date) = to_date('2020-06-29', 'yyyy-MM-dd')
 group by t.emp_id
 order by t.emp_id asc

-- 查询某系统某天的闲置用户
select r.emp_id
  from REGISTER_USER r
 where r.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and r.emp_id not in
       (select t.emp_id
          from ACTIVE_USER t, REGISTER_USER x
         where t.emp_id = x.emp_id
           and t.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
           and x.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
           and trunc(t.active_date) = to_date('2020-06-29', 'yyyy-MM-dd')
         group by t.emp_id)
 order by r.emp_id asc

-- 查询某系统某个时间段的活跃用户
select t.emp_id
  from ACTIVE_USER t, REGISTER_USER x
 where t.emp_id = x.emp_id
   and t.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and x.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and t.active_date >= to_date('2020-06-01', 'yyyy-MM-dd')
   and t.active_date < to_date('2020-07-01', 'yyyy-MM-dd')
 group by t.emp_id
 order by t.emp_id asc

-- 查询某系统某段时间的闲置用户
select r.emp_id
  from REGISTER_USER r
 where r.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
   and r.emp_id not in
       (select t.emp_id
          from ACTIVE_USER t, REGISTER_USER x
         where t.emp_id = x.emp_id
           and t.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
           and x.system_info_id = 'A11B902ADB06CCE8E050F40A5BE72308'
           and t.active_date >= to_date('2020-06-01', 'yyyy-MM-dd')
           and t.active_date < to_date('2020-07-01', 'yyyy-MM-dd')
         group by t.emp_id)
 order by r.emp_id asc
