import React from 'react';
import { history } from 'umi';
import { Avatar } from 'antd';
import { UserOutlined } from '@ant-design/icons';

import styles from './detailContentStyle.less';

export default function DetailContent() {
  const content: any = history.location.state;

  return (
    <div>
      <div>
        <Avatar size={40} icon={<UserOutlined />} />
        <span className={styles.empno}>C3005884</span>
      </div>
      <div className={styles.title}>{content.title}</div>
      <div className={styles.time}>{content.time}</div>
      <div className={styles.diver}></div>
      <div className={styles.content}>{content.content}</div>
      <div className={styles.contentFooter}>
        <a className={styles.link}>删除帖子</a>
      </div>
    </div>
  );
}
