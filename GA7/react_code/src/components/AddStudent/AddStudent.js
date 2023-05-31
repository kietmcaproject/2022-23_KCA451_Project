import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";

function AddStudent() {
  const [userData, setUserdata] = useState([]);

  useEffect(() => {
    const getUserdata = async () => {
      const reqData = await fetch(
        "http://localhost:8080/student/getAllStudents"
      );
      const resData = await reqData.json();
      setUserdata(resData);
      // console.log(resData);
    };
    getUserdata();
  }, []);

  function refreshPage() {
    window.location.reload(false);
  }

  function handleDelete(email) {
    fetch(`http://localhost:8080/student/deleteStudent/${email}`, {
      method: "DELETE",
    }).then((result) => {
      result.json().then((resp) => {
        console.log(resp);
      });
    });
    refreshPage();
  }

  return (
    <div>
      <React.Fragment>
        <AdminDash />
        <Header>
          <div className="container">
            <div className="row">
              <div className="col-md-9">
                <div className="d-grid d-md-flex justify-content-md-end mb-3">
                  <Link to="/addStudent" className="button">
                    Add Student
                  </Link>
                </div>
                <br />
                <br />
                <table className="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>Email</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Course</th>
                    </tr>
                  </thead>
                  <tbody>
                    {userData.map((userData, index) => (
                      <tr key={index}>
                        <td>{userData.email} </td>
                        <td>{userData.firstName} </td>
                        <td>{userData.lastName} </td>
                        <td>{userData.course} </td>
                        <td>
                          <button
                            onClick={() => alert(userData.email)}
                            className="btn btn-success mx-auto"
                          >
                            Edit
                          </button>{" "}
                          &nbsp; &nbsp;
                          <button
                            onClick={() => handleDelete(userData.email)}
                            className="btn btn-danger"
                          >
                            Delete
                          </button>
                          &nbsp; &nbsp;
                          <Link
                            to={"/showSub/" + userData.email}
                            className="btn btn-warning"
                          >
                            Show Subjects
                          </Link>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
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
  top: 20%;
`;

export default AddStudent;
