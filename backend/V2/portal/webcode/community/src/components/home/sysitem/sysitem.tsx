import React from 'react';

import { ListItemType } from '@/components/home/syslist/sysList';
import styles from './sysItemStyle.less';

interface SysProps {
  item: ListItemType
}

export default function SysItem({ item }: SysProps) {
  return (
    <>
      <img className={styles.icon} src={item.icon} alt={item.title} />
      <span className={styles.title}>{item.title}</span>
      <span className={styles.type}>{item.typeText}</span>
      <br />
      <span className={styles.article}>帖子：{item.article}</span>
      <span className={styles.watch}>瀏覽：{item.watch}</span>
    </>
  );
}
