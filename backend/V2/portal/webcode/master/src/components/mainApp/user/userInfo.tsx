import React, {FC} from 'react';
import { UserOutlined, DownOutlined } from '@ant-design/icons';
import { Avatar, Menu, Dropdown } from 'antd';
import styles from './userStyle.less';

const menu = (
  <Menu className={styles.menu}>
    <Menu.Item>
      <a target="_blank" rel="noopener noreferrer" href="">
        退出登錄
      </a>
    </Menu.Item>
  </Menu>
);

export default function UserInfo() {
  return (
    <Dropdown overlay={menu}>
      <span>
        <Avatar size={26} icon={<UserOutlined />} />
        <span className={styles.empno}>C3005993</span>
        <DownOutlined />
      </span>
    </Dropdown>
  )
}