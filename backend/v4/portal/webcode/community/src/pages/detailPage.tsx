import React from 'react';
import DetailContent from '@/components/detail/content/detailContent';
import SearchBar from '@/components/home/search/searchBar';
import DetailComment from '@/components/detail/comment/detailComment';

export default function DetailPage() {
  return (
    <div className="community-app">
      <div className="community-content-search">
        <SearchBar btnText="搜索" />
      </div>
      <div className="community-content community-bg-light">
        <DetailContent />
      </div>
      <div className="community-content community-bg-light" style={{ marginTop: '10px' }}>
        <DetailComment />
      </div>
    </div>
  )
}