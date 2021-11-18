import React, { FC, useState, useEffect } from 'react';
import { UserOutlined, DownOutlined } from '@ant-design/icons';
import { Avatar, Menu, Dropdown } from 'antd';
import styles from './userStyle.less';
import { useModel } from 'umi';
import {getUserInfo} from "../../../http/user/userService"
const url = window.location.origin + "/portal/logout"
const menu = (

  <Menu className={styles.menu}>
    <Menu.Item>
      <a rel="noopener noreferrer" href={url}>
        退出登錄
      </a>
    </Menu.Item>
  </Menu>
);
export default function UserInfo() {
  const [empName, setEmpName] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      const result = await getUserInfo()
      setEmpName(result.empName);
      localStorage.setItem('user',JSON.stringify(result))
    };
    fetchData();
  }, []);
  return (
    <Dropdown overlay={menu}>
      <span>
        <Avatar size={26} icon={<UserOutlined />} />
          <span className={styles.empno}>{empName}</span>
        <DownOutlined />
      </span>
    </Dropdown>
  )
}