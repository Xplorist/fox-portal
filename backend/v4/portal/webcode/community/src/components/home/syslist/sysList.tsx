import React from 'react';
import { history } from 'umi';

import styles from './sysListStyle.less';
import GoldIcon from '@/assets/images/gold.png';
import QisIcon from '@/assets/images/qis.png';
import MouldIcon from '@/assets/images/mould.png';
import ToolsIcon from '@/assets/images/tools.png';
import HumanIcon from '@/assets/images/human.png';
import ManagerIcon from '@/assets/images/manager.png';
import HumantIcon from '@/assets/images/humant.png';
import SysItem from '@/components/home/sysitem/sysitem';

export interface ListItemType {
  id: string;
  title: string;
  typeText: string;
  article: number;
  watch: number;
  icon: string;
}

const list: ListItemType[] = [
  {
    id: 'a',
    title: '金流系統',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: GoldIcon,
  },
  {
    id: 'b',
    title: '品質系統',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: QisIcon,
  },
  {
    id: 'c',
    title: '模治檢具',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: MouldIcon,
  },
  {
    id: 'd',
    title: '智能工具管理',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: ToolsIcon,
  },
  {
    id: 'e',
    title: '人事系統',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: HumanIcon,
  },
  {
    id: 'f',
    title: '工管系統',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: ManagerIcon,
  },
  {
    id: 'g',
    title: '點名系統',
    typeText: '社區',
    article: 20,
    watch: 1500,
    icon: HumantIcon,
  },
];

export default function SysList() {
  const onItemClick = (item: ListItemType) => {
    history.push('/article', item);
  };
  return (
    <div className={styles.content}>
      <div className={styles.row}>
        {list.map((item: ListItemType) => (
          <div key={item.id} className={styles.col} onClick={() => onItemClick(item)}>
            <SysItem item={item} />
          </div>
        ))}
      </div>
    </div>
  );
}
