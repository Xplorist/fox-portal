import React from 'react';

import styles from './demandListStyle.less';

interface Comment {
  id: string;
  des: string;
  time: string;
}

interface ListItem {
  id: string;
  num: number;
  title: string;
  des: string;
  time: string;
  status: number;
  statusText: string;
  statusColor: string;
  comment: Comment[];
}

const list: ListItem[] = [
  {
    id: 'a',
    num: 1,
    title: '幫忙新增品質系統KPI功能。',
    des: 'Mac(I)製造二處金加廠模修',
    time: '2020-05-15 10:12:30',
    status: 1,
    statusColor: '#818896',
    statusText: '已回復',
    comment: [
      {
        id: 'a',
        des: '你好我們已經在開發過程中了。',
        time: '2020-05-15 10:12:30',
      },
      {
        id: 'b',
        des: '敬請查看開發計劃及進度。',
        time: '2020-05-14 15:29:06',
      },
    ],
  },
  {
    id: 'b',
    num: 2,
    title: '幫忙新增生管系統功能。',
    des: 'Mac(I)製造二處金加廠模修',
    time: '2020-05-15 10:12:30',
    status: 0,
    statusColor: '#1264FF',
    statusText: '待回复',
    comment: [],
  },
  {
    id: 'c',
    num: 3,
    title: '幫忙新增品質系統KPI，幫忙新增品質系 統KPI幫忙新增品質系統KPI功能。',
    des: 'Mac(I)製造二處金加廠模修',
    time: '2020-05-15 10:12:30',
    status: 1,
    statusColor: '#818896',
    statusText: '已回復',
    comment: [
      {
        id: 'a',
        des: '你好我們已經在開發過程中了。',
        time: '2020-05-15 10:12:30',
      },
    ],
  },
  {
    id: 'd',
    num: 4,
    title: '幫忙添加SOP駁回功能。',
    des: 'Mac(I)製造二處金加廠模修',
    time: '2020-05-15 10:12:30',
    status: 1,
    statusColor: '#818896',
    statusText: '已回復',
    comment: [
      {
        id: 'a',
        des: '你好我們已經在開發過程中了。',
        time: '2020-05-15 10:12:30',
      },
    ],
  },
];

export default function DemandList() {
  return (
    <ul className={styles.list}>
      {list.map((item, index) => (
        <li
          className={styles.listItem}
          key={item.id}
          style={index === 0 ? { border: 'none ' } : {}}
        >
          <div className={styles.titleContent}>
            <div className={styles.num}>{item.num}、</div>
            <div className={styles.title}>{item.title}</div>
            <div className={styles.status} style={{ color: item.statusColor }}>
              {item.statusText}
            </div>
          </div>
          <div>
            <span className={styles.des}>{item.des}</span>
            <div className={styles.time}>{item.time}</div>
          </div>
          {item.comment.length > 0 && (
            <div className={styles.comment}>
              <p>最近回復</p>
              <ul className={styles.commentList}>
                {item.comment.map(comment => (
                  <li key={comment.id}>
                    <div>{comment.des}</div>
                    <p>{comment.time}</p>
                  </li>
                ))}
              </ul>
            </div>
          )}
        </li>
      ))}
    </ul>
  );
}
