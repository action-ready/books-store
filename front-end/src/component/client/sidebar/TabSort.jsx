import { Tabs } from "antd";
import { useSelector } from "react-redux";
const onChange = (key) => {
  console.log(key);
};
const items = [
  {
    key: "1",
    label: "Phổ Biến",
  },
  {
    key: "2",
    label: "Hàng Mới",
  },
  {
    key: "3",
    label: "Giá Thấp Đến Cao",
  },
  {
    key: "4",
    label: "Giá Cao Đến Thấp",
  },
];
function TabSort() {
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
