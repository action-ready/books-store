import axios from "../../../utils/axiosCustomize";

const getAllCompany = () => {
  let data = axios.get("/companies");
  return data;
};

export { getAllCompany };
