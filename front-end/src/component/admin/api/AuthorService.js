import axios from "../../../utils/axiosCustomize";

const getAllAuthor = () => {
  let data = axios.get("/authors");
  return data;
};

export { getAllAuthor };
