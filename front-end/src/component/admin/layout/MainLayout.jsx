import Navbar from "../navbar/Navbar";
import Sidebars from "../sidebar/Sidebar";

function MainLayout({ children }) {
  return (
    <div style={{ overflowX: "hidden" }}>
      <Navbar />
      <div
        className=""
        style={{
          display: "flex",
          height: "100%",
          minHeight: "100vh",
        }}
      >
        <Sidebars />
        <div className="flex-grow-1">{children}</div>
      </div>
    </div>
  );
}

export default MainLayout;
