import React from 'react';
import { UserOutlined } from '@ant-design/icons';
import { Input, Button, Avatar } from 'antd';

import styles from './commentTreeStyle.less';

export interface CommentTreeLeafType {
  id: string;
  empno: string;
  time: string;
  content: string;
  isRoot?: boolean;
  children: CommentTreeLeafType[];
}

interface CommentTreeProps {
  tree: CommentTreeLeafType;
}

export default function CommentTree({ tree }: CommentTreeProps) {
  const style = tree.isRoot ? {} : { marginLeft: '100px' };
  return (
    <div style={style}>
      <div>
        <Avatar size={40} icon={<UserOutlined />} />
        <span className={styles.empno}>{tree.empno}</span>
        <span className={styles.time}>{tree.time}</span>
      </div>
      <div className={styles.textDark}>{tree.content}</div>
      <div>
        <button className={styles.btnMuted}>回復</button>
      </div>
      <div className={styles.diver}></div>
      {tree.children.map(item => <CommentTree key={item.id} tree={item} />)}
    </div>
  );
}
