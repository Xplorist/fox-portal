import React, { FC } from 'react';
import { ConnectProps, IndexModelState, connect, BoxItem } from 'umi';

import styles from './homeContentStyle.less';
import StatIcon from '@/assets/images/stat.png';
import SysIcon from '@/assets/images/sys.png';
import DefaultIcon from '@/assets/images/manager.png'

interface HomeContentPorps extends ConnectProps {
  indexModel: IndexModelState;
}

const HomeContent: FC<HomeContentPorps> = ({ indexModel }) => {
  const BoxList = indexModel.boxList;
  const route = (path: string) => window.location.href = path;
  return (
    <div className={styles.content}>
      {BoxList.map((boxItem: BoxItem) => (
        <div className={styles.box} key={boxItem.id}>
          <div className={styles.card}>
            <img src={boxItem.picSrc || DefaultIcon} className={styles.icon} />
            <span className={styles.title}>{boxItem.name || '-'}</span>
            <br />
            <span className={styles.muted}>{boxItem.summary || '-'}</span>
          </div>
          <div className={styles.controlBox}>
            <div className={styles.controlBody}>
              <div className={styles.controlCol} onClick={() => route(boxItem.url)}>
                <img src={SysIcon} className={styles.img} />
                <br />
                <span>進入系統</span>
              </div>
              <div className={styles.controlCol}>
                <img src={StatIcon} className={styles.img} />
                <br />
                <span>系統分析</span>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default connect(({ indexModel }: { indexModel: IndexModelState }) => ({
  indexModel,
}))(HomeContent);
