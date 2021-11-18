import React from 'react';
import { DownOutlined } from '@ant-design/icons';

import styles from './homeRightStyle.less';
import DemandList from '../demand/demandList';

export default function HomeRight() {
  return (
    <div className={styles.right}>
      <div className={styles.title}>
        需求互動
        <div className={styles.titleRight}>
          全部
          <DownOutlined className={styles.titleRightIcon} />
        </div>
      </div>
      <div className={styles.content}>
        <DemandList />
      </div>
    </div>
  )
}