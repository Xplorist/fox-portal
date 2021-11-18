import React from 'react';
// import { Badge } from 'antd';

import styles from './mainNavbarStyle.less';
import UserInfo from '../user/userInfo';
import NavList from '../navlist/navlist';

export default function MainNavbar() {
  return (
    <div className={styles.navbar}>
      <div className={styles.left}>門戶系統</div>
      <nav className={styles.nav}>
        <NavList />
      </nav>
      <div className={styles.right}>
        <span className={styles.mr}>
          {/* <Badge dot>消息</Badge> */}
          
        </span>
        <UserInfo />
      </div>
    </div>
  );
}
