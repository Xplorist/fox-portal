import React from 'react';

import styles from './homeContentStyle.less';
import SysList from '../syslist/sysList';

const str: string = '請不要發佈與工作無關的內容及廣告，一旦發現立即清除並封號，更不要洩露公司機密，一旦發現，移送司法機關處理！';

export default function HomeContent() {
  return (
    <div>
      <p className={styles.tip}>{str}</p>
      <div className={styles.line}></div>
      <SysList />
    </div>
  )
}