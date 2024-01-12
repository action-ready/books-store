import axios from "../../../utils/axiosCustomize";

const getAllTranslator = () => {
  let data = axios.get("/translators");
  return data;
};

export { getAllTranslator };
