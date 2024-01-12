import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "../utils/axiosCustomize";

export const fetchBooks = createAsyncThunk("bookList/fetchBooks", async () => {
  let data = await axios.get("/books");

  return data;
});

export const createBook = createAsyncThunk(
  "bookList/createBook",
  async (data) => {
    let formData = new FormData();
    formData.append("name", data.name);
    formData.append("purchasePrice", data.purchasePrice);
    formData.append("sellingPrice", data.sellingPrice);
    formData.append("pageCount", data.pageCount);
    formData.append("quantity", data.quantity);

    formData.append("publicationDate", data.publicationDate);
    formData.append("description", data.description);
    formData.append("companyId", data.companyId);
    formData.append("publishersId", data.publishersId);
    formData.append("coverTypesId", data.coverTypesId);
    formData.append("translatorId", data.translatorId);
    formData.append("authorId", data.authorId);
    formData.append("categoryId", data.categoryId);
    formData.append("status", "ACTIVE");
    data.files.forEach((file, index) => {
      formData.append(
        `files[${index}]`,
        file instanceof File ? file : file.file
      );
    });
    let res = await axios.post("/books", formData);

    return res;
  }
);

const bookSlice = createSlice({
  name: "bookList",
  initialState: {
    status: "idle",
    books: [],
    searchText: "",
  },
  reducers: {
    setSearchText: (state, action) => {
      state.searchText = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchBooks.pending, (state, action) => {
        state.status = "loading";
      })
      .addCase(fetchBooks.fulfilled, (state, action) => {
        (state.status = "idle"), (state.books = action.payload.data);
      })
      .addCase(createBook.pending, (state, action) => {
        state.status = "loading";
      })
      .addCase(createBook.fulfilled, (state, action) => {
        state.status = "idle";
      });
  },
});

export default bookSlice;
