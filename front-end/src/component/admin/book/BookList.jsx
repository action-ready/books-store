import { Table } from "antd";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchBooks } from "../../../slices/bookSlice";
import { FaEdit } from "react-icons/fa";
import { MdDelete } from "react-icons/md";

const columns = [
  {
    title: "#",
    render: (text, record, index) => index + 1,
    width: "5%",
  },
  {
    title: "Name",
    dataIndex: "name",
    key: "name",

    width: "15%",
  },
  {
    title: "Giá",
    dataIndex: "sellingPrice",
    key: "sellingPrice",
    width: "5%",
  },
  {
    title: "Số Lượng",
    dataIndex: "quantity",
    key: "quantity",
    width: "5%",
  },
  {
    title: "Tác Giả",
    dataIndex: "authorName",
    key: "authorName",
    width: "15%",
  },
  {
    title: "Ngày Xuất Bản",
    dataIndex: "publicationDate",
    key: "publicationDate",
    width: "15%",
    render: (text) => {
      const date = new Date(text);
      const formattedDate = date.toISOString().split("T")[0];
      return formattedDate;
    },
  },
  {
    title: "Action",

    width: "15%",
    render: () => {
      return (
        <>
          <FaEdit size={"1.5em"} />
          <span className="mx-2"></span>
          <MdDelete size={"1.5em"} color="red" />
        </>
      );
    },
  },
];

function BookList() {
  const dispatch = useDispatch();
  const [pagination, setPagination] = useState({
    pageSize: 6,
    showSizeChanger: false,
  });

  useEffect(() => {
    dispatch(fetchBooks());
  }, [dispatch]);

  const books = useSelector((state) => state.bookList.books);
  return (
    <>
      <Table dataSource={books} columns={columns} pagination={pagination} />
    </>
  );
}

export default BookList;
