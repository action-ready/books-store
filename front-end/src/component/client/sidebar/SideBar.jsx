import { InputNumber } from "antd";
import { useEffect, useState } from "react";
import { getAllCategory } from "../../admin/api/CategoryService";
import { getAllAuthor } from "../../admin/api/AuthorService";
import { getAllCompany } from "../../admin/api/CompanyService";
import { getAllPublishers } from "../../admin/api/PublishersService";
import { getAllCoverTypes } from "../../admin/api/CoverTypesService";
import { useDispatch } from "react-redux";
import bookFilter from "../../../slices/bookFilterSlice";

function SideBar() {
  const [categories, setCategories] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [companies, setCompanies] = useState([]);
  const [publishers, setPublishers] = useState([]);
  const [coverTypeses, setCoverTypeses] = useState([]);

  const dispatch = useDispatch();
  useEffect(() => {
    fetchData();
  }, []);
  const fetchData = async () => {
    try {
      let data = await getAllCategory();
      setCategories(data.data._embedded.categories);
    } catch (error) {
      console.error("Error fetching categories:", error);
    }

    try {
      let data = await getAllAuthor();
      setAuthors(data.data._embedded.authors);
    } catch (error) {
      console.error("Error fetching Authors:", error);
    }

    try {
      let data = await getAllCompany();
      setCompanies(data.data._embedded.companies);
    } catch (error) {
      console.error("Error fetching companies:", error);
    }
    try {
      let data = await getAllPublishers();
      setPublishers(data.data._embedded.publisherses);
    } catch (error) {
      console.error("Error fetching companies:", error);
    }

    try {
      let data = await getAllCoverTypes();
      setCoverTypeses(data.data._embedded.coverTypeses);
    } catch (error) {
      console.error("Error fetching companies:", error);
    }
  };

  const onSearchCategory = (cate) => {
    dispatch(bookFilter.actions.setCategoryName(cate.name));
    const categoryId = cate._links.category.href.split("/").slice(-1)[0];
    dispatch(bookFilter.actions.setCategoryId(categoryId));
  };

  const onSearchAuthor = (e, author) => {
    const isChecked = e.target.checked;

    const authorId = author._links.author.href.split("/").slice(-1)[0];

    if (!isChecked) {
      dispatch(bookFilter.actions.removeAuthorId(authorId));
    } else {
      dispatch(bookFilter.actions.setAuthorId(authorId));
    }
  };

  return (
    <div className="col-md-2 bg-light ">
      <div className="row border-bottom">
        <h6 className="text-center py-2">Danh Mục Sản Phẩm</h6>
        {categories?.map((cate) => {
          return (
            <>
              <button
                key={cate}
                className="py-1 text-center"
                style={{ fontSize: "14px", color: "rgb(56, 56, 61)" }}
                onClick={() => onSearchCategory(cate)}
              >
                {cate.name}
              </button>
            </>
          );
        })}
      </div>

      <div className="row border-bottom">
        <h6 className="ms-3 py-2">Giá</h6>
        <span
          style={{ width: "173px" }}
          className="py-1 ms-4 my-2 text-white  text-center bg-secondary rounded "
        >
          Dưới 30.000
        </span>
        <span
          style={{ width: "173px" }}
          className="py-1 my-2 ms-4 text-white   text-center bg-secondary rounded "
        >
          Dưới 30.000
        </span>
        <span
          style={{ width: "173px" }}
          className="py-1 my-2 ms-4 text-white   text-center bg-secondary rounded "
        >
          Dưới 30.000
        </span>
        <span
          style={{ width: "173px" }}
          className="py-1 my-2 ms-4 text-white   text-center bg-secondary rounded "
        >
          Dưới 30.000
        </span>
        <h6 className="ms-3 py-2">Chọn Khoảng Giá</h6>
        <div className="d-flex  justify-content-center">
          <InputNumber min={1} max={10} defaultValue={3} className="me-2" /> -
          <InputNumber min={1} max={10} defaultValue={3} className="ms-2" />
        </div>
        <button
          className="btn btn-outline-primary mt-3 ms-3 mb-3"
          style={{ width: "190px" }}
        >
          Áp Dụng
        </button>
      </div>

      <div className="row border-bottom">
        <h6 className="text-center py-2">Công Ty Phát Hành</h6>
        {companies?.map((company) => {
          return (
            <>
              <div className="form-check ms-3">
                <input
                  className="form-check-input"
                  type="checkbox"
                  defaultValue=""
                  id="flexCheckDefault"
                  value={company.id}
                />
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  {company.name}
                </label>
              </div>
            </>
          );
        })}
      </div>

      <div className="row border-bottom">
        <h6 className="text-center py-2">Tác Giả</h6>
        {authors?.map((author) => {
          return (
            <>
              <div className="form-check ms-3">
                <input
                  className="form-check-input"
                  type="checkbox"
                  defaultValue=""
                  id="flexCheckDefault"
                  value={author}
                  onChange={(e) => onSearchAuthor(e, author)}
                  checked={author.isChecked}
                />
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  {author.name}
                </label>
              </div>
            </>
          );
        })}
      </div>

      <div className="row border-bottom ">
        <h6 className="text-center py-2">Loại Bìa</h6>
        {coverTypeses?.map((company) => {
          return (
            <>
              <div className="form-check ms-3">
                <input
                  className="form-check-input"
                  type="checkbox"
                  defaultValue=""
                  id="flexCheckDefault"
                  value={company.id}
                />
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  {company.name}
                </label>
              </div>
            </>
          );
        })}
      </div>

      <div className="row border-bottom ">
        <h6 className="text-center py-2">Nhà Cung Cấp</h6>
        {publishers?.map((company) => {
          return (
            <>
              <div className="form-check ms-3">
                <input
                  className="form-check-input"
                  type="checkbox"
                  defaultValue=""
                  id="flexCheckDefault"
                  value={company.id}
                />
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  {company.name}
                </label>
              </div>
            </>
          );
        })}
      </div>
    </div>
  );
}

export default SideBar;
