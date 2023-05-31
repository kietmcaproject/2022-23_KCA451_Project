import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";
import React from "react";
import Assignment_1 from "../../assets/images/PDF/Assignment_1.pdf";
import Assignment1_Ans from "../../assets/images/PDF/Assignment1_Ans.pdf";
import { Link } from "react-router-dom";

function ViewAssignment() {
  return (
    <div>
      <React.Fragment>
        <AdminDash />
        <Header>
          <div>
            <a href={Assignment_1} target="_blank" rel="noreferrer">
              Assignment 1
            </a>
          </div>
          <br></br>
          <div>
            <a href={Assignment1_Ans} target="_blank" rel="noreferrer">
              Student Assignment
            </a>{" "}
          </div>
          <Link id="route" to={"/markAssignment"} className="button">
            Back
          </Link>
        </Header>
      </React.Fragment>
    </div>
  );
}
const Header = styled.div`
  position: absolute;
  left: 30%;
  top: 10%;
`;

export default ViewAssignment;
