import React from 'react';

import SearchBar from '@/components/home/search/searchBar';
import ArticleContent from '@/components/article/content/articleContent';

export default function ArticlePage() {
  return (
    <div className="community-app">
      <div className="community-content-search">
        <SearchBar btnText="搜索" />
      </div>
      <div className="community-content community-bg-light">
        <ArticleContent />
      </div>
    </div>
  )
}