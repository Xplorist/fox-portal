import React, { useEffect, useRef } from 'react';

import styles from './loadingStyle.less';

export default function TopLoading() {
  const ref: any = useRef();
  useEffect(() => {
    const el = document.getElementById('master-top-progress');
    if (el) document.body.removeChild(el);
    setTimeout(() => {
      ref.current.style.width = '100%';
    });
  }, []);
  return (
    <div ref={ref} className={styles.loadingStyle}></div>
  )
}