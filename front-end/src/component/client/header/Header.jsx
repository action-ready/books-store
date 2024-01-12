import { Link } from "react-router-dom";
import { CiUser } from "react-icons/ci";
import { CiShop } from "react-icons/ci";
import "./Header.css";
function Header() {
  return (
    <div className=" align-items-center row" id="header">
      <div className="col-lg-2">
        <Link>
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/2300px-React-icon.svg.png"
            alt=""
            className="py-3"
            style={{ width: "80px", height: "80px" }}
          />
        </Link>
      </div>
      <div className="col-lg-8 ps-5">
        <Link
          className="border-end px-3 "
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          Home
        </Link>
        <Link
          className="border-end px-3"
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          ABOUT US
        </Link>
        <Link
          to={"/books"}
          className="border-end px-3"
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          BOOKS{" "}
        </Link>
        <Link
          className="border-end px-3"
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          NEW RELEASE
        </Link>
        <Link
          className="border-end px-3"
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          CONTACT US
        </Link>
        <Link
          className="px-3"
          style={{ color: "inherit", textDecoration: "inherit" }}
        >
          BLOG
        </Link>
      </div>
      <div className="col-lg-2 ">
        <div className="px-5">
          <CiUser size={"1.5em"} className=" me-3" />
          <CiShop size={"1.5em"} />
        </div>
      </div>
    </div>
  );
}

export default Header;
