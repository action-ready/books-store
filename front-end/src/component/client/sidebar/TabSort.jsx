import { Tabs } from "antd";
import { useDispatch, useSelector } from "react-redux";
import bookFilter from "../../../slices/bookFilterSlice";

const items = [
  {
    key: "id",
    label: "Phổ Biến",
  },
  {
    key: "name",
    label: "Hàng Mới",
  },
  {
    key: "price",
    label: "Giá Thấp Đến Cao",
  },
  {
    key: "company",
    label: "Giá Cao Đến Thấp",
  },
];
function TabSort() {
  const distPatch = useDispatch();
  const onChange = (key) => {
    distPatch(bookFilter.actions.setSort(key));
  };
  const categoryName = useSelector((state) => state.bookFilter.categoryName);
  return (
    <div className=" bg-light ms-1" style={{ height: "100px" }}>
      <h5 className="pt-2 ps-2">{categoryName}</h5>
      <Tabs
        className="ps-1"
        defaultActiveKey="1"
        items={items}
        onChange={onChange}
      ></Tabs>
    </div>
  );
}

export default TabSort;
