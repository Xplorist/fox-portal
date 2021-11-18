import React from 'react';
import { history } from 'umi';
import { DownOutlined } from '@ant-design/icons';
import { Pagination } from 'antd';

import styles from './articleContentStyle.less';
import SysItem from '@/components/home/sysitem/sysitem';
import ArticleList from '../list/articleList';

export default function ArticleContent() {
  const sys: any = history.location.state;
  return (
    <div>
      <div className={styles.title}>
        <SysItem item={sys} />
        <button className={styles.btnRight}>
          最多回复
          <DownOutlined className={styles.icon} />
        </button>
      </div>
      <ArticleList />
      <div className={styles.pagenation}>
        <Pagination size="small" total={50} />
      </div>
    </div>
  );
}
