import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";

function ShowSubject() {
  const { email } = useParams();
  const [userData, setUserdata] = useState([]);

  useEffect(() => {
    const request = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(),
    };
    fetch("http://localhost:8080/student/getAllSubjects/" + email, request)
      .then((response) => response.json())
      .then((detail) => {
        setUserdata(detail);
      });
  }, []);

  return (
    <div>
      <React.Fragment>
        <AdminDash />
        <Header>
          <div className="container">
            <div className="row">
              <div className="col-md-9">
                <div className="d-grid d-md-flex justify-content-md-end mb-3">
                  <Link id="route" to={"/student"} className="button">
                    Back
                  </Link>
                  <br />
                  <br />
                  <h5>subjects of student {email} are: </h5>
                  <br />
                  <table className="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Subject Code</th>
                        <th>Subject Name</th>
                      </tr>
                    </thead>
                    <tbody>
                      {userData.map((userData, index) => (
                        <tr key={index}>
                          <td>{userData.subjectCode} </td>
                          <td>{userData.subjectName} </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
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

export default ShowSubject;
