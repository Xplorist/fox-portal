import React from 'react';
import SearchBar from '@/components/home/search/searchBar';
import HomeContent from '@/components/home/content/homeContent';

export default function HomePage() {
  return (
    <div className="community-app">
      <div className="community-content community-bg-light">
        <SearchBar btnText="搜社區" />
        <HomeContent />
      </div>
    </div>
  );
}
