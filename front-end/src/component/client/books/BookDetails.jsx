import { useParams } from "react-router-dom";
import MainLayout from "../layout/MainLayout";
import { FaAngleDown } from "react-icons/fa6";
import { Rate } from "antd";
import { SiApacheopenoffice } from "react-icons/si";
import { FaStar } from "react-icons/fa";
import { useEffect, useState } from "react";
import { getBookById } from "../../admin/api/BookService";

function BookDetails() {
  const { id } = useParams();
  const [book, setBook] = useState(null);
  const [price, setPrice] = useState(" ");
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    fetchBookById();
  }, [id]);

  const fetchBookById = async () => {
    let data = await getBookById(id);
    console.log(`<<<<<  data >>>>>`, data);
    setBook(data.data);
    setPrice(book?.sellingPrice);
  };
  let formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });
  function formatDate(dateString) {
    const options = { day: "2-digit", month: "2-digit", year: "numeric" };
    return new Date(dateString).toLocaleDateString("en-GB", options);
  }
  return (
    <MainLayout>
      <div className="col-md-3 me-2" style={{ backgroundColor: "#F5F5FA" }}>
        <div className="my-2">
          <img
            src={book?.bookImageUrl}
            style={{ width: "100%", height: "400px" }}
            className="img-thumbnail"
          />
        </div>
        <div className="row ">
          {book?.bookImageUrl?.map((img) => {
            return (
              <div key={img} className="col-md-3">
                <a href="">
                  <img
                    src={img}
                    alt=""
                    style={{ height: "50px" }}
                    className="img-thumbnail my-2"
                  />
                </a>
              </div>
            );
          })}
        </div>
      </div>
      <div className="col-md-5 me-2" style={{ backgroundColor: "#F5F5FA" }}>
        <div
          className="m-2 p-2 rounded-1"
          style={{ backgroundColor: "#ffffff" }}
        >
          <div className="mt-2">
            <span className=" fw-bold text-primary me-3">
              <FaAngleDown
                className="bg-primary text-white rounded-5 me-1"
                size={"1em"}
              />
              Chính Hãng
            </span>
            <span>
              Tác Giả: <a className="text-primary"> {book?.authorName}</a>
            </span>
            <h5 className="my-2">{book?.name}</h5>

            <span className="text-black fw-bold">
              5.0{" "}
              <Rate
                className="me-2"
                disabled
                defaultValue={5}
                style={{ marginTop: "-45px", fontSize: "15px" }}
              />
              (135) | Đã Bán: 123
            </span>
            <div>
              <span
                className="text-black"
                style={{ fontSize: "25px", fontWeight: "bold" }}
              >
                {formatter.format(book?.sellingPrice)}
              </span>
            </div>
          </div>
        </div>

        <div
          className="m-2 p-2 rounded-1 mt-4"
          style={{ backgroundColor: "#ffffff" }}
        >
          <div className="mt-2">
            <h6 className="pb-2">Thông tin chi tiết</h6>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}> Công ty phát hành</span>{" "}
              <span style={{ color: "#565658", marginLeft: "150px" }}>
                {book?.companyName}
              </span>
              <hr />
            </p>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}>Ngày xuất bản</span>
              <span style={{ color: "#565658", marginLeft: "180px" }}>
                {book && formatDate(book?.publicationDate)}
              </span>
              <hr />
            </p>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}> Dịch Giả</span>{" "}
              <span style={{ color: "#565658", marginLeft: "280px" }}>
                {book?.translatorName}
              </span>
              <hr />
            </p>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}> Loại bìa</span>{" "}
              <span style={{ color: "#565658", marginLeft: "230px" }}>
                {book?.coverTypesName}
              </span>
              <hr />
            </p>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}>Số trang</span>{" "}
              <span style={{ color: "#565658", marginLeft: "225px" }}>
                {book?.pageCount}
              </span>
              <hr />
            </p>
            <p className="pt-0">
              <span style={{ color: "#ADADB3" }}>Nhà xuất bản</span>{" "}
              <span style={{ color: "#565658", marginLeft: "180px" }}>
                {book?.publishersName}
              </span>
              <hr />
            </p>
          </div>
        </div>

        <div
          className="m-2 p-2 rounded-1 mt-4"
          style={{ backgroundColor: "#ffffff" }}
        >
          <div className="mt-2">
            <h6>Mô tả sản phẩm</h6>
            <div className="">{book?.description}</div>
          </div>
        </div>
      </div>
      <div className="col-md-3 " style={{ backgroundColor: "#F5F5FA" }}>
        <div className="row">
          <div className="col-md-3 my-3">
            <a href="">
              <img
                src="https://vcdn.tikicdn.com/cache/w100/ts/seller/21/ce/5c/b52d0b8576680dc3666474ae31b091ec.jpg.webp"
                alt=""
                className="rounded-5"
              />
            </a>
          </div>

          <div className="col-md-9 my-3">
            <span>
              Tiki Trading <SiApacheopenoffice />
            </span>
            <p className="pt-2">
              4.7 <FaStar size={"1.2em"} style={{ color: "yellow" }} /> (5.4tr+
              đánh giá)
            </p>
          </div>
          <hr />
          <div className="row">
            <h6 className="fw-bold">Số Lượng</h6>
            <div className="">
              {quantity === 1 ? (
                <button className="btn border " disabled>
                  <span style={{ fontSize: "25px" }} disabled>
                    -
                  </span>
                </button>
              ) : (
                <button
                  className="btn border "
                  onClick={() => setQuantity(quantity - 1)}
                >
                  <span style={{ fontSize: "25px" }} disabled>
                    -
                  </span>
                </button>
              )}

              <button className="btn border mx-3">
                <span style={{ fontSize: "25px" }}>{quantity}</span>
              </button>
              <button
                className="btn border "
                onClick={() => setQuantity(quantity + 1)}
              >
                <span style={{ fontSize: "25px" }}>+</span>
              </button>
            </div>
            <h6 className="fw-bold mt-2">Tạm tính</h6>
            {quantity === 1 ? (
              <h2>{formatter.format(book?.sellingPrice)}</h2>
            ) : (
              <h2>{formatter.format(book?.sellingPrice * quantity)}</h2>
            )}

            <button className="btn btn-primary ms-2 mt-2">Mua Ngay</button>
            <button className="btn border-primary bg-white text-primary ms-2 mt-2">
              Thêm Vào Giỏ Hàng
            </button>
          </div>
        </div>
      </div>
    </MainLayout>
  );
}

export default BookDetails;
