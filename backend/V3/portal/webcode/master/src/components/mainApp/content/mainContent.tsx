import React from 'react';

import styles from './mainContentStyle.less';

export default class MainContent extends React.Component {
  render() {
    return (
      <div id="root-subapp" className={styles.mainContent}></div>
    )
  }
}