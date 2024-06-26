import { Route, Routes } from "react-router-dom";
import ManageBook from "./component/admin/book/ManageBook";
import DashBoard from "./component/admin/dashBoard/DashBoard";

import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./component/client/page/Home";
import Books from "./component/client/page/Books";
import BookDetails from "./component/client/books/BookDetails";
function App() {
  return (
    <>
      <Routes>
        <Route path="/admin" element={<DashBoard />} />
        <Route path="/admin/books" element={<ManageBook />} />
      </Routes>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/books" element={<Books />} />
        <Route path="/books/:id" element={<BookDetails />} />
      </Routes>
    </>
  );
}

export default App;
