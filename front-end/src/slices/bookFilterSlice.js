import { createSlice } from "@reduxjs/toolkit";

const bookFilter = createSlice({
  name: "bookFilter",
  initialState: {
    categoryName: "",
    categoryId: "",
    authorId: [],
  },
  reducers: {
    setCategoryId: (state, action) => {
      state.categoryId = action.payload;
    },
    setAuthorId: (state, action) => {
      state.authorId.push(action.payload);
    },
    removeAuthorId: (state, action) => {
      state.authorId = state.authorId.filter((id) => id !== action.payload);
    },
    setCategoryName: (state, action) => {
      state.categoryName = action.payload;
    },
  },
});

export default bookFilter;
