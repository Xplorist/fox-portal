import React, { FC } from 'react';


import MainNavbar from '@/components/mainApp/navbar/mainNavbar';
import MainFooter from '@/components/mainApp/footer/mainFooter';
import MainContent from '@/components/mainApp/content/mainContent';
import TopLoading from '@/components/mainApp/loading/loading';

const MainAppPage: FC<any> = () => {
  return (
    <div>
      <TopLoading />
      <MainNavbar />
      <MainContent />
      <MainFooter />
    </div>
  );
};

export default MainAppPage;