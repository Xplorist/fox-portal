import React from 'react';

import styles from './mainFooterStyle.less';

export default function MainFooter() {
  return (
    <div className={styles.footer}>
      <span className={styles.textInfo}>技術：智能雲網開發（成都）分部</span>
      <span className={styles.textInfo}>地點：富士康科技（成都）園區D區23棟3樓西側</span>
      <span className={styles.textInfo}>電話：5073-66812</span>
    </div>
  )
}