import axios from "../../../utils/axiosCustomize";

const getAllPublishers = () => {
  let data = axios.get("/publisherses");
  return data;
};

export { getAllPublishers };
