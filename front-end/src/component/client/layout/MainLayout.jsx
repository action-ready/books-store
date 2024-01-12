import Header from "../header/Header";
import Footer from "../footer/Footer";

function MainLayout({ children }) {
  return (
    <div className="container ">
      <div className="container-figure">
        <div className="row">
          <Header />
        </div>
      </div>
      <div className="row ">{children}</div>
      <div className="row">
        <Footer />
      </div>
    </div>
  );
}

export default MainLayout;
