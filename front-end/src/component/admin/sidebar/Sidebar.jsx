import { Sidebar, Menu, MenuItem } from "react-pro-sidebar";
import { Link } from "react-router-dom";
import "./Sidebar.css";
function Sidebars() {
  return (
    <>
      <Sidebar>
        <Menu>
          <MenuItem>
            <Link to={"/admin"} className="no-underline">
              Trang Chủ
            </Link>
          </MenuItem>
          <MenuItem>
            <Link to={"/admin/books"} className="no-underline">
              Quản Lý Sản Phẩm
            </Link>
          </MenuItem>
          <MenuItem> E-commerce</MenuItem>
          <MenuItem> Examples</MenuItem>
        </Menu>
      </Sidebar>
    </>
  );
}

export default Sidebars;
