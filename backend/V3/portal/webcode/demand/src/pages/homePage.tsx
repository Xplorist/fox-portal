import React from 'react';

import styles from '@/assets/style.less';
import HomeLeft from '@/components/home/left/homeLeft';
import HomeRight from '@/components/home/right/homeRight';

export default function HomePage() {
  return (
    <div className={styles.demandApp}>
      <div className={styles.demandContent}>
        <HomeLeft />
        <HomeRight />
      </div>
    </div>
  );
}
