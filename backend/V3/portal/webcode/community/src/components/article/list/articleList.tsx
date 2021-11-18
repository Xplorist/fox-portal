import React from 'react';
import { history } from 'umi';

import styles from './articleListStyle.less';

interface ArtilcleType {
  id: string;
  title: string;
  des: string;
  time: string;
  content: string;
}

const articleData: ArtilcleType[] = [
  {
    id: 'a',
    title: 'SpringBoot Whitelabel Error Page的根本原因，三种解决方案以及其特点',
    des:
      '在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解，这样理解起来更加方便，如果需要知道最直接的解决方案，拖到最底部看样例代码即可。',
    time: '2020-04-11 10:19:07',
    content: `在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解
    ，这样理解起来更加方便，
    如果需要知道最直接的解决方案，拖到最底部 看样例代码即可。
    介绍了的白页出现的真正原因，
    主要是没有合适的匹配情况出现404情况，
    然后跳转到系统默认的第一个ErrorPage，
    也就是白 页内容上，
    然后根据其特定分别从三个角度，
    1、拦截器，2、新ErrorPage，
    3、自定义/error路由 去解决该问题，并且介绍各自方法的优缺点，
     其中还有介绍到循环页面错误的本质原因等情况`,
  },
  {
    id: 'b',
    title: 'SpringBoot Whitelabel Error Page的根本原因，三种解决方案以及其特点',
    des:
      '在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解，这样理解起来更加方便，如果需要知道最直接的解决方案，拖到最底部看样例代码即可。',
    time: '2020-04-11 10:19:07',
    content: `在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解
    ，这样理解起来更加方便，
    如果需要知道最直接的解决方案，拖到最底部 看样例代码即可。
    介绍了的白页出现的真正原因，
    主要是没有合适的匹配情况出现404情况，
    然后跳转到系统默认的第一个ErrorPage，
    也就是白 页内容上，
    然后根据其特定分别从三个角度，
    1、拦截器，2、新ErrorPage，
    3、自定义/error路由 去解决该问题，并且介绍各自方法的优缺点，
     其中还有介绍到循环页面错误的本质原因等情况`,
  },
  {
    id: 'c',
    title: 'SpringBoot Whitelabel Error Page的根本原因，三种解决方案以及其特点',
    des:
      '在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解，这样理解起来更加方便，如果需要知道最直接的解决方案，拖到最底部看样例代码即可。',
    time: '2020-04-11 10:19:07',
    content: `在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解
    ，这样理解起来更加方便，
    如果需要知道最直接的解决方案，拖到最底部 看样例代码即可。
    介绍了的白页出现的真正原因，
    主要是没有合适的匹配情况出现404情况，
    然后跳转到系统默认的第一个ErrorPage，
    也就是白 页内容上，
    然后根据其特定分别从三个角度，
    1、拦截器，2、新ErrorPage，
    3、自定义/error路由 去解决该问题，并且介绍各自方法的优缺点，
     其中还有介绍到循环页面错误的本质原因等情况`,
  },
  {
    id: 'd',
    title: 'SpringBoot Whitelabel Error Page的根本原因，三种解决方案以及其特点',
    des:
      '在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解，这样理解起来更加方便，如果需要知道最直接的解决方案，拖到最底部看样例代码即可。',
    time: '2020-04-11 10:19:07',
    content: `在学习这个学习笔记之前最好能够对spring mvc以及Tomcat有些了解
    ，这样理解起来更加方便，
    如果需要知道最直接的解决方案，拖到最底部 看样例代码即可。
    介绍了的白页出现的真正原因，
    主要是没有合适的匹配情况出现404情况，
    然后跳转到系统默认的第一个ErrorPage，
    也就是白 页内容上，
    然后根据其特定分别从三个角度，
    1、拦截器，2、新ErrorPage，
    3、自定义/error路由 去解决该问题，并且介绍各自方法的优缺点，
     其中还有介绍到循环页面错误的本质原因等情况`,
  },
];

export default function ArticleList() {
  const onItemClick = (item: any) => {
    history.push('/detail', item);
  }
  return (
    <ul className={styles.list}>
      {articleData.map((item: ArtilcleType) => (
        <li key={item.id} onClick={() => onItemClick(item)}>
          <p className={styles.title}>{item.title}</p>
          <p>{item.des}</p>
          <p>{item.time}</p>
        </li>
      ))}
    </ul>
  );
}
