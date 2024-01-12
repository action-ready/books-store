import BookList from "../books/BookList";
import TabSort from "./TabSort";

function Content() {
  return (
    <div className="col-md-10 ">
      <TabSort />
      <BookList />
    </div>
  );
}

export default Content;
