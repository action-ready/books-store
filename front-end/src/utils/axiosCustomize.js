import axios from "axios";
const QuotesClient = axios.create({
  baseURL: "http://localhost:8080/api/v1",
});

export default QuotesClient;
