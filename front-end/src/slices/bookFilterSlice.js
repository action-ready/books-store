import { createSlice } from "@reduxjs/toolkit";

const bookFilter = createSlice({
  name: "bookFilter",
  initialState: {
    categoryName: "",
    categoryId: "",
    authorId: [],
    minPrice: "",
    maxPrice: "",
    companyId: [],
    coverTypes: [],
    publishers: [],
    sort: "",
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
    setMinPrice: (state, action) => {
      state.minPrice = action.payload;
    },
    setMaxPrice: (state, action) => {
      state.maxPrice = action.payload;
    },
    setCompanyId: (state, action) => {
      state.companyId.push(action.payload);
    },
    setRemoveCompanyId: (state, action) => {
      state.companyId = state.companyId.filter((id) => id !== action.payload);
    },
    setCoverTypes: (state, action) => {
      state.coverTypes.push(action.payload);
    },
    setRemoveCoverTypes: (state, action) => {
      state.coverTypes = state.coverTypes.filter((id) => id !== action.payload);
    },
    setPublishers: (state, action) => {
      state.publishers.push(action.payload);
    },
    setRemovePublishers: (state, action) => {
      state.publishers = state.coverTypes.filter((id) => id !== action.payload);
    },
    setSort: (state, action) => {
      state.sort = action.payload;
    },
  },
});

export default bookFilter;
