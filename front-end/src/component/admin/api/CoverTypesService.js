import axios from "../../../utils/axiosCustomize";

const getAllCoverTypes = () => {
  let data = axios.get("/coverTypeses");
  return data;
};

export { getAllCoverTypes };
