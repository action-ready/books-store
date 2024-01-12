import "./Banner.css";

function Banner() {
  return (
    <div
      className="col-lg-12 row border-start  border-end rounded"
      id={"banners"}
    >
      <div className="col-lg-7 ">
        <div className="" id={"content-left"}>
          <h1>Ipsum Dolor Si</h1>
          <p className="pt-0">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam sint
            suscipit quasi neque sapiente. Possimus, quis ullam omnis placeat
            itaque rem doloremque dicta suscipit commodi, maxime, fugit
            similique facilis laboriosam.
          </p>
          <button className="btn btn-outline-secondary btn-lg px-4">
            Read more
          </button>
        </div>
      </div>
      <div className="col-lg-5 ">
        <img
          id={"image-banner"}
          src="https://img.freepik.com/premium-psd/world-book-day-celebration-banner-template_47987-15441.jpg"
          alt=""
        />
      </div>
    </div>
  );
}

export default Banner;
