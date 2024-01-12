import { configureStore } from "@reduxjs/toolkit";
import bookSlice from "../slices/bookSlice";
import bookFilterSlice from "../slices/bookFilterSlice";

const store = configureStore({
  reducer: {
    bookList: bookSlice.reducer,
    bookFilter: bookFilterSlice.reducer,
  },
});

export default store;
