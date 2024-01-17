import axios from "../../../utils/axiosCustomize";

const getAllBook = () => {
  let data = axios.get("/books");
  return data;
};

const getAllBookFilter = (request) => {
  let data = axios.get("/books/filters", {
    params: request,
  });
  return data;
};

const getBookById = (id) => {
  let data = axios.get("/books/" + id);
  return data;
};
export { getAllBook, getAllBookFilter, getBookById };
