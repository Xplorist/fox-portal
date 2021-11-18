import React from 'react';

import styles from './searchBarStyle.less';

interface SearchBarProps {
  btnText: string;
}

export default function SearchBar({ btnText }: SearchBarProps) {
  return (
    <div className={styles.searchBar}>
      <div className={styles.inputGroup}>
        <input className={styles.input} />
        <button className={styles.btn}>{btnText}</button>
      </div>
      <button className={styles.rightBtn}>發帖</button>
    </div>
  );
}
