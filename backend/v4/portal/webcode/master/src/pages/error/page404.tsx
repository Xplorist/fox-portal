import React from 'react';
import { Link } from 'umi';
import constans from '@/constans';

const styles = {
  link: {
    fontSize: '20px',
    color: '#fff',
    background: '#3A7EFF',
    padding: '10px 20px',
    margin: '0 auto',
    width: '150px',
    display: 'block',
    marginTop: '20px',
    borderRadius: '2px',
    boxShadow: '1px 1px 1px #bbbbbc',
    point: 'cursor'
  },
};

export default function Page404() {
  return (
    <div style={{ textAlign: 'center', paddingTop: '30vh' }}>
      <span style={{ fontSize: '24px' }}>404,頁面未找到！</span>
      <Link to={constans.MAIN_HOME + '/view/home'} style={styles.link}>
        返回首頁
      </Link>
    </div>
  );
}
