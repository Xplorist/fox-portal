import React from 'react';

import styles from './HomeNavStyle.less';

interface NavLink {
  id: string;
  name: string;
  path: string;
}

const NavList: NavLink[] = [
  {
    id: 'a',
    name: '常用系統',
    path: ''
  },
  {
    id: 'b',
    name: '全部系統',
    path: ''
  }
];

export default function HomeNav() {
  return (
    <ul className={styles.navContent}>
      {NavList.map((link: NavLink) => <li key={link.id}>{link.name}</li>)}
    </ul>
  )
}