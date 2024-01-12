import axios from "../../../utils/axiosCustomize";

const getAllCategory = () => {
  let data = axios.get("/categories");
  return data;
};

export { getAllCategory };
