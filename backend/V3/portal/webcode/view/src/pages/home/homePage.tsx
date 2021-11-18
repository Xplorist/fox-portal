import React from 'react';
import HomePictrue from '@/components/home/picture/homePicture';
import HomeNav from '@/components/home/nav/homeNav';
import HomeContent from '@/components/home/content/homeContent';

export default function HomePage() {
  return (
    <div className="view-app">
      <div className="view-content">
        <HomePictrue />
        <HomeNav />
        <HomeContent />
      </div>
    </div>
  );
}
