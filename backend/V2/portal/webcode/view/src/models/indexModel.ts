import { Effect, Reducer, Subscription } from 'umi';

import { getSystemList } from '@/components/home/content/homeContentService';

export interface BoxItem {
  id: string;
  name: string;
  summary: string;
  picSrc: string;
  url: string;
}

export interface IndexModelState {
  boxList: BoxItem[];
}

export interface IndexModelType {
  namespace: 'indexModel';
  state: IndexModelState;
  effects: {
    query: Effect;
  };
  reducers: {
    save: Reducer<IndexModelState>;
  };
  subscriptions: { setup: Subscription };
}

const IndexModel: IndexModelType = {
  namespace: 'indexModel',
  state: {
    boxList: [],
  },
  effects: {
    *query({ payload }, { call, put }) {
      const list = yield call(getSystemList);
      yield put({ type: 'save', payload: { boxList: list } });
    },
  },
  reducers: {
    save(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname }) => {
        if (pathname === '/home') {
          dispatch({
            type: 'query',
          });
        }
      });
    },
  },
};

export default IndexModel;
