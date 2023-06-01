import "./util.css";

export const Button = ({ title }) => {
  return (
    <button className="button-green" type="submit">
      {title}
    </button>
  );
};
