import React from 'react';

import BacgroundPic from '@/assets/images/background.png';
import styles from './homePictrueStyle.less';

export default function HomePictrue() {
  return (
    <div className={styles.imgContent}>
      <img src={BacgroundPic} alt="background" />
    </div>
  )
}