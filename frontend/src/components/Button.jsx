import React from "react";
import PropTypes from "prop-types";

const Button = ({ label, onClick, variant = "primary", size = "md" }) => (
  <button
    onClick={onClick}
    className={`btn btn-${variant} btn-${size} m-2`}
  >
    {label}
  </button>
);

Button.propTypes = {
  label: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
  variant: PropTypes.string, 
  size: PropTypes.string, 
};

export default Button;
