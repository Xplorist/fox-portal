import React from 'react';
import { RightOutlined } from '@ant-design/icons';

import styles from './homeLeftStyle.less';

interface ListItem {
  id: string;
  title: string;
}

const data: ListItem[] = [
  {
    id: 'a',
    title: '生管系統',
  },
  {
    id: 'b',
    title: '點名系統',
  },
];

function ListItem({ item, index }: any) {
  const style = index === 0 ? { border: 'none' } : {};
  return (
    <li style={style}>
      <span className={styles.link}>{item.title}</span>
      <RightOutlined className={styles.floatRight} />
    </li>
  );
}

export default function HomeLeft() {
  return (
    <div className={styles.left}>
      <div className={styles.inputGroup}>
        <input
          className={styles.input}
          placeholder="請輸入問題或需求如：生管"
        />
        <button className={styles.searchBtn}>搜索</button>
      </div>
      <p className={styles.tip}>没有找到需求，是否新增？</p>
      <ul className={styles.list}>
        {data.map((item: ListItem, index: number) => (
          <ListItem key={item.id} item={item} index={index} />
        ))}
      </ul>
    </div>
  );
}
