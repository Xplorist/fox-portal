import React from 'react';

import { Input, Button, Pagination } from 'antd';

import styles from './detailCommentStyle.less';
import CommentTree, {
  CommentTreeLeafType,
} from '@/components/detail/tree/commentTree';

const tree: CommentTreeLeafType = {
  isRoot: true,
  id: 'a',
  empno: 'C3005940',
  time: '2020-04-11 10:19:07',
  content: '说的好666。',
  children: [
    {
      id: 'b',
      empno: 'C3005940',
      time: '2020-04-11 10:19:07',
      content: '白页内容会展示状态码、path、以及错误原因等情况，但是真正发。',
      children: []
    },
    {
      id: 'c',
      empno: 'C3005940',
      time: '2020-04-11 10:19:07',
      content: '就是SpringBoot中HTTP请求出现异常的说明页',
      children: []
    },
  ],
};

export default function DetailComment() {
  return (
    <div>
      <Input.TextArea rows={4} placeholder="输入评论"></Input.TextArea>
      <div className={styles.textRight}>
        <Button type="primary">确定</Button>
      </div>
      <div className={styles.tip}>全部评论</div>
      <CommentTree tree={tree} />
      <div className={styles.pagenation}>
        <Pagination size="small" total={50} />
      </div>
    </div>
  );
}
