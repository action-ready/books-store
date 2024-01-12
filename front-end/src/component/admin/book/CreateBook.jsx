import { DatePicker, Input, InputNumber, Modal, Select } from "antd";
import { useEffect, useState } from "react";
import { getAllCategory } from "../api/CategoryService";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import ReactImageUploading from "react-images-uploading";
import { getAllAuthor } from "../api/AuthorService";
import { getAllCompany } from "../api/CompanyService";
import { getAllPublishers } from "../api/PublishersService";
import { getAllTranslator } from "../api/TranslatorService";
import { getAllCoverTypes } from "../api/CoverTypesService";
import { useDispatch, useSelector } from "react-redux";
import { createBook, fetchBooks } from "../../../slices/bookSlice";
import moment from "moment";
import { toast } from "react-toastify";

function CreateBook({ isModalOpen, setIsModalOpen }) {
  const dispatch = useDispatch();

  const [name, setName] = useState("");
  const [purchasePrice, setPurchasePrice] = useState("");
  const [sellingPrice, setSellingPrice] = useState("");
  const [pageCount, setPageCount] = useState("");
  const [quantity, setQuantity] = useState("");
  const [publicationDate, setPublicationDate] = useState("");
  const [description, setDescription] = useState("");
  const [companyId, setCompanyId] = useState("");
  const [publishersId, setPublishersId] = useState("");
  const [coverTypesId, setCoverTypesId] = useState("");
  const [translatorId, setTranslatorId] = useState("");
  const [authorId, setAuthorId] = useState("");
  const [categoryId, setCategoryId] = useState("");

  const [categories, setCategories] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [companies, setCompanies] = useState([]);
  const [publishers, setPublishers] = useState([]);
  const [translators, setTranslators] = useState([]);
  const [coverTypeses, setCoverTypeses] = useState([]);
  const [files, setFiles] = useState([]);
  const maxNumber = 69;

  const books = useSelector((state) => state.bookList);

  const onChange = (imageList, addUpdateIndex) => {
    // data for submit
    console.log(imageList, addUpdateIndex);
    const fileArray = imageList.map((image) => image.file);
    setFiles(fileArray);
  };
  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    if (books.status === "idle") {
      dispatch(fetchBooks());
    }
  }, [books.status, dispatch]);

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
      let data = await getAllTranslator();
      setTranslators(data.data._embedded.translators);
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

  function getIdFromUrl(url) {
    const splitted = url.split("/");
    return splitted[splitted.length - 1];
  }

  const handleDateChange = (date, dateString) => {
    const formattedDate = moment(dateString).format("YYYY-MM-DD");
    setPublicationDate(formattedDate);
  };

  const handleOk = () => {
    let data = {
      name: name,
      purchasePrice: purchasePrice,
      sellingPrice: sellingPrice,
      pageCount: pageCount,
      quantity: quantity,
      publicationDate: publicationDate,
      description: description,
      companyId: companyId,
      publishersId: publishersId,
      coverTypesId: coverTypesId,
      translatorId: translatorId,
      authorId: authorId,
      categoryId: categoryId,
      status: "ACTIVE",
      files: files,
    };

    dispatch(createBook(data));
    toast.success("Thêm Mới Thành Công");

    // setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
    <>
      <Modal
        title="Thêm Mới Sách"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <div className="container">
          <div className="row mb-2">
            <div className="col-lg-6 ">
              <label htmlFor="" className="py-1">
                Tên Sách
              </label>
              <Input
                placeholder="Nhập Tên sách"
                onChange={(e) => setName(e.target.value)}
                style={{ width: "100%", height: "36px" }}
              />
            </div>
            <div className="col-lg-6">
              <label htmlFor="" className="py-1">
                Tên Tác Giả
              </label>
              <select
                onChange={(event) => setAuthorId(Number(event.target.value))}
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn Tên Tác Giả</option>
                {authors?.map((cate) => {
                  return (
                    <>
                      <option
                        key={cate._links.self.href}
                        value={getIdFromUrl(cate._links.self.href)}
                      >
                        {cate.name}
                      </option>
                    </>
                  );
                })}
              </select>
            </div>
          </div>

          <div className="row mb-2">
            <div className="col-lg-6 ">
              <div htmlFor="" className="py-1">
                Giá Nhập
              </div>
              <InputNumber
                onChange={(value) => setPurchasePrice(value)}
                style={{ width: "100%", height: "36px" }}
                min={1}
                placeholder="Nhập giá"
              />
            </div>
            <div className="col-lg-6">
              <div htmlFor="" className="py-1">
                Giá Bán
              </div>
              <InputNumber
                onChange={(value) => setSellingPrice(value)}
                style={{ width: "100%", height: "36px" }}
                min={1}
                placeholder="Nhập giá "
              />
            </div>
          </div>

          <div className="row mb-2">
            <div className="col-lg-6 ">
              <div htmlFor="" className="py-1">
                Số Trang
              </div>
              <InputNumber
                onChange={(value) => setPageCount(value)}
                style={{ width: "100%", height: "36px" }}
                min={1}
                placeholder="Nhập số trang "
              />
            </div>
            <div className="col-lg-6">
              <div htmlFor="" className="py-1">
                Số Lượng
              </div>
              <InputNumber
                onChange={(value) => setQuantity(value)}
                style={{ width: "100%", height: "36px" }}
                min={1}
                placeholder="Nhập số lượng "
              />
            </div>
          </div>

          <div className="row mb-2">
            <div className="col-lg-6 ">
              <div htmlFor="" className="py-1">
                Ngày Xuất Bản
              </div>
              <DatePicker
                onChange={handleDateChange}
                style={{ width: "100%", height: "36px" }}
                placeholder="Nhập ngaỳ xuất bản "
              />
            </div>
            <div className="col-lg-6">
              <div htmlFor="" className="py-1">
                Tên Công Ty
              </div>

              <select
                onChange={(event) => setCompanyId(Number(event.target.value))}
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn tên công ty</option>
                {companies?.map((cate) => {
                  return (
                    <>
                      <option
                        key={cate._links.self.href}
                        value={getIdFromUrl(cate._links.self.href)}
                      >
                        {cate.name}
                      </option>
                    </>
                  );
                })}
              </select>
            </div>
          </div>

          <div className="row mb-2">
            <div className="col-lg-6 ">
              <div htmlFor="" className="py-1">
                Nhà Xuất Bản
              </div>

              <select
                onChange={(event) =>
                  setPublishersId(Number(event.target.value))
                }
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn nhà xuất bản</option>
                {publishers?.map((cate) => {
                  return (
                    <>
                      <option
                        key={cate._links.self.href}
                        value={getIdFromUrl(cate._links.self.href)}
                      >
                        {cate.name}
                      </option>
                    </>
                  );
                })}
              </select>
            </div>
            <div className="col-lg-6">
              <div htmlFor="" className="py-1">
                Tên Loại Bìa
              </div>

              <select
                onChange={(event) =>
                  setCoverTypesId(Number(event.target.value))
                }
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn loại bìa</option>
                {coverTypeses?.map((cate) => {
                  return (
                    <>
                      <option
                        key={cate._links.self.href}
                        value={getIdFromUrl(cate._links.self.href)}
                      >
                        {cate.name}
                      </option>
                    </>
                  );
                })}
              </select>
            </div>
          </div>

          <div className="row mb-4">
            <div className="col-lg-6 ">
              <div htmlFor="" className="py-1">
                Người Phiên Dịch
              </div>
              <select
                onChange={(event) =>
                  setTranslatorId(Number(event.target.value))
                }
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn người phiên dịch</option>
                {translators?.map((cate) => {
                  return (
                    <>
                      <option
                        key={cate._links.self.href}
                        value={getIdFromUrl(cate._links.self.href)}
                      >
                        {cate.name}
                      </option>
                    </>
                  );
                })}
              </select>
            </div>
            <div className="col-lg-6">
              <div htmlFor="" className="py-1">
                Thể Loại
              </div>
              <select
                onChange={(event) => setCategoryId(Number(event.target.value))}
                className="form-select"
                aria-label="Default select example"
              >
                <option selected>Chọn thể loại</option>
                {categories?.map((cate) => (
                  <option
                    key={cate._links.self.href}
                    value={getIdFromUrl(cate._links.self.href)}
                  >
                    {cate.name}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="row mb-5">
            <h3 style={{ marginLeft: "102px" }}>Mô Tả Sản Phẩm</h3>
            <ReactQuill
              theme="snow"
              style={{ height: "150px" }}
              value={description}
              onChange={setDescription}
            />
          </div>
          <div className="row border">
            <ReactImageUploading
              style={{ height: "150px" }}
              multiple
              value={files}
              onChange={onChange}
              maxNumber={maxNumber}
              dataURLKey="data_url"
            >
              {({
                imageList,
                onImageUpload,
                onImageRemoveAll,
                onImageUpdate,
                onImageRemove,
                isDragging,
                dragProps,
              }) => (
                // write your building UI
                <div className="upload__image-wrapper py-3 px-3">
                  <button
                    style={isDragging ? { color: "red" } : undefined}
                    onClick={onImageUpload}
                    {...dragProps}
                  >
                    Click or Drop here
                  </button>
                  &nbsp;
                  <button onClick={onImageRemoveAll}>Remove all images</button>
                  {imageList.map((image, index) => (
                    <div key={index} className="image-item py-2">
                      <img src={image["data_url"]} alt="" width="100" />
                      <div className="image-item__btn-wrapper mt-2">
                        <button onClick={() => onImageUpdate(index)}>
                          Update
                        </button>
                        <button onClick={() => onImageRemove(index)}>
                          Remove
                        </button>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </ReactImageUploading>
          </div>
        </div>
      </Modal>
    </>
  );
}

export default CreateBook;
