import "./TopCategory.css";
import { FaArrowRight } from "react-icons/fa";

function TopCategory() {
  return (
    <div className="col-lg-12 " id="content">
      <h3 className="text-center">Explore our Top Categories</h3>
      <div className="row mt-5">
        <div className="col-lg-4 justify-content-center">
          <div className="d-flex flex-column align-items-center">
            <img
              className="rounded float-start image-size"
              src="https://img.freepik.com/free-photo/book-composition-with-open-book_23-2147690555.jpg"
              alt=""
            />
            <p className="text-center pt-3">Higher Education</p>
          </div>
        </div>
        <div className="col-lg-4 justify-content-center">
          <div className="d-flex flex-column align-items-center">
            <img
              className="rounded float-start image-size"
              src="https://img.freepik.com/free-photo/book-composition-with-open-book_23-2147690555.jpg"
              alt=""
            />
            <p className="text-center pt-3">Higher Education</p>
          </div>
        </div>
        <div className="col-lg-4 justify-content-center">
          <div className="d-flex flex-column align-items-center">
            <img
              className="rounded float-start image-size"
              src="https://img.freepik.com/free-photo/book-composition-with-open-book_23-2147690555.jpg"
              alt=""
            />
            <p className="text-center pt-3">Higher Education</p>
          </div>
        </div>
      </div>
      <div className="text-center">
        <button className="btn btn-outline-secondary px-4">
          <span>
            View more <FaArrowRight />
          </span>
        </button>
      </div>
    </div>
  );
}

export default TopCategory;
