import { useCallback, useEffect, useState } from "react";
import { getAllBookFilter } from "../../admin/api/BookService";
import Book from "./Book";
import { useSelector } from "react-redux";

function BookList() {
  const [books, setBooks] = useState([]);
  const categoryId = useSelector((state) => state.bookFilter.categoryId);
  const authorId = useSelector((state) => state.bookFilter.authorId);
  const companyId = useSelector((state) => state.bookFilter.companyId);
  const coverTypesId = useSelector((state) => state.bookFilter.coverTypes);
  const publishersId = useSelector((state) => state.bookFilter.publishers);
  const minPrice = useSelector((state) => state.bookFilter.minPrice);
  const maxPrice = useSelector((state) => state.bookFilter.maxPrice);
  const sort = useSelector((state) => state.bookFilter.sort);
  const fetchBooks = useCallback(async () => {
    let request = {
      size: 12,
    };
    if (minPrice > 0) {
      request.minPrice = minPrice;
    }
    if (maxPrice > 0) {
      request.maxPrice = maxPrice;
    }
    if (categoryId) {
      request.category = categoryId;
    }
    if (authorId.length > 0) {
      request.author = authorId.join(",");
    }
    if (companyId.length > 0) {
      request.company = companyId.join(",");
    }
    if (coverTypesId.length > 0) {
      request.coverTypes = coverTypesId.join(",");
    }
    if (publishersId.length > 0) {
      request.publishers = publishersId.join(",");
    }
    if (sort) {
      request.sort = sort;
    }
    let data = await getAllBookFilter(request);
    setBooks(data.data);
  }, [
    categoryId,
    authorId,
    minPrice,
    maxPrice,
    companyId,
    coverTypesId,
    publishersId,
    sort,
  ]);

  const fetchBooksAll = useCallback(async () => {
    let request = {
      size: 12,
    };

    let data = await getAllBookFilter(request);
    setBooks(data.data);
  }, []);

  useEffect(() => {
    fetchBooks();
  }, [fetchBooks]);

  useEffect(() => {
    fetchBooksAll();
  }, [fetchBooksAll]);
  return (
    <>
      <div className="mt-2 row bg-light ms-1">
        {books?.content?.map((book) => {
          return (
            <>
              <Book book={book} key={book} />
            </>
          );
        })}
      </div>
      <div className="d-flex pt-3 justify-content-center">
        <nav aria-label="Page navigation example" className=" ">
          <ul className="pagination">
            <li className="page-item">
              <a className="page-link" href="#">
                Previous
              </a>
            </li>
            <li className="page-item">
              <a className="page-link" href="#">
                1
              </a>
            </li>
            <li className="page-item">
              <a className="page-link" href="#">
                2
              </a>
            </li>
            <li className="page-item">
              <a className="page-link" href="#">
                3
              </a>
            </li>
            <li className="page-item">
              <a className="page-link" href="#">
                Next
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </>
  );
}

export default BookList;
