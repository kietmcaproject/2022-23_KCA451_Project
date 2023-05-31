import React from "react";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

const MarkAssignment = () => {
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
          {" "}
          <div className="container">
            <div className="row">
              <div className="col-md-9">
                <div className="d-grid d-md-flex justify-content-md-end mb-3">
                  <Link id="route" to={"/mark"} className="button">
                    Back
                  </Link>
                  <Link id="route" to={"/"} className="button">
                    Add More
                  </Link>
                  <br />
                  <br />

                  <br />
                  <table className="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Subject Code</th>
                        <th>Subject Name</th>
                        <th>Assignment Type</th>
                        <th> Assignment Marks</th>
                        <th>Max Assignment Marks</th>
                      </tr>
                    </thead>
                    <tbody>
                      {userData.map((userData, index) => (
                        <tr key={index}>
                          <td>{userData.subjectCode} </td>
                          <td>{userData.subjectName} </td>
                          <td>{userData.assignType}</td>
                          <td>{userData.obtainedAssignMarks} </td>
                          <td>{userData.maxAssignMarks} </td>

                          <td>
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            <Link
                              to={"/editAssignments/" + userData.id}
                              className="btn btn-warning"
                            >
                              Update
                            </Link>
                            &nbsp; &nbsp; &nbsp; &nbsp;
                            <Link
                              to={"/viewAssignment/" + userData.subjectCode}
                              className="btn btn-warning"
                            >
                              View Submission
                            </Link>
                          </td>
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
};
const Header = styled.div`
  position: absolute;
  left: 30%;
  top: 10%;
`;

export default MarkAssignment;
