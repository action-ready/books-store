import MainLayout from "../layout/MainLayout";
import Content from "../sidebar/Content";
import SideBar from "../sidebar/SideBar";

function Books() {
  return (
    <MainLayout>
      <SideBar />
      <Content />
    </MainLayout>
  );
}

export default Books;
