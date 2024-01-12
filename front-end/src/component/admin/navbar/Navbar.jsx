import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div className=" align-items-center border" style={{ height: "8vh" }}>
      <div className="row">
        <div className="col-lg-3 ">
          <h1 style={{ height: "10px", marginBottom: "5px" }}>
            <Link>
              <img
                style={{
                  width: "80px",
                  marginLeft: "65px",
                }}
                alt=""
              />
            </Link>
          </h1>
        </div>
      </div>
    </div>
  );
}

export default Navbar;
