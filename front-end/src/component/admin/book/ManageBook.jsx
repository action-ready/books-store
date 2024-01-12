import MainLayout from "../layout/MainLayout";
import "./ManageBook.css";
import BookList from "./BookList";
import { useState } from "react";
import CreateBook from "./CreateBook";
import { Button, Input } from "antd";
function ManageBook() {
  const [isModalOpen, setIsModalOpen] = useState(false);

  return (
    <>
      <MainLayout>
        <div id="main">
          <div id="contents" className="container ">
            <h3>Quản Lý Sản Phẩm</h3>
            <div className="row mx-1 py-2 align-items-center border">
              <div className="col-lg-4 ">
                <Input placeholder="Tìm Kiếm" />
              </div>
              <div className="col-lg-4"></div>
              <div className="col-lg-2 ">
                <Button type="primary" onClick={() => setIsModalOpen(true)}>
                  Thêm Mới
                </Button>
              </div>
            </div>
            <div className="row  mt-3 mx-1" id="book-list">
              <BookList />
            </div>
          </div>
        </div>

        <CreateBook isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} />
      </MainLayout>
    </>
  );
}
export default ManageBook;
