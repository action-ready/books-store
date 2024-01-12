import { Rate } from "antd";
import { FaAngleDown } from "react-icons/fa6";
import { Link } from "react-router-dom";

function Book({ book }) {
  const { name, sellingPrice } = book;
  return (
    <div className="col p-2 ms-4">
      <div className="card " style={{ width: "18rem", height: "500px" }}>
        <Link to={"/s"}>
          <img
            src="https://salt.tikicdn.com/cache/280x280/ts/product/75/91/36/4d917ab5d82b0cfcfe77f336dd005ad8.jpg.webp"
            className="card-img-top"
            alt="..."
          />
          <div className="row ">
            <div className="col pt-2 ms-4">
              <span className=" fw-bold text-primary ">
                <FaAngleDown
                  className="bg-primary text-white rounded-5 me-1"
                  size={"1em"}
                />
                Chính Hãng
              </span>
            </div>
            <div className="col">
              <span className="btn bg-light mb-2 ">Tài Trợ</span>
            </div>
          </div>
          <div className="row">
            <span className=" text-center pt-0 px-1 text-black">{name}</span>
            <h6>
              <Rate
                className="ms-5 "
                disabled
                defaultValue={5}
                style={{ marginTop: "-45px", fontSize: "15px" }}
              />
              <span className="ms-2">Đã Bán:123</span>
            </h6>
          </div>

          <div className="card-body">
            <span className=" text-black fw-bold">
              {sellingPrice} VNĐ{" "}
              <span className="text-black btn bg-light" disabled>
                -37%
              </span>
            </span>
          </div>
        </Link>
      </div>
    </div>
  );
}

export default Book;
