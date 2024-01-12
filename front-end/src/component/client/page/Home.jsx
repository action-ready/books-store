import Banner from "../content/Banner";
import NewBook from "../content/NewBook";
import TopCategory from "../content/TopCategory";
import MainLayout from "../layout/MainLayout";
function Home() {
  return (
    <MainLayout>
      <Banner />
      <TopCategory />
      <NewBook />
    </MainLayout>
  );
}

export default Home;
