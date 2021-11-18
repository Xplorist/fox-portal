import React from 'react';
import styles from './navlistStyle.less';
import constans from '@/constans';

interface LinkType {
  id: string;
  name: string;
  path: string;
}

const home = constans.BASE_URL + constans.MAIN_HOME.slice(1);

const navList: LinkType[] = [
  {
    id: 'a',
    name: '首頁',
    path: home + '/view/home',
  },
  {
    id: 'b',
    name: '系統統計',
    path: home + '/statistics/home',
  },
  {
    id: 'c',
    name: '用戶需求',
    path: home + '/demand/home',
  },
  {
    id: 'd',
    name: '自助社區',
    path: home + '/community/home',
  },
];

export default function NavList() {
  return (
    <ul className={styles.navlink}>
      {navList.map((link: LinkType) => (
        <li key={link.id} className={styles.link}>
          <a href={link.path}>{link.name}</a>
          <div className={styles.box}></div>
        </li>
      ))}
    </ul>
  );
}