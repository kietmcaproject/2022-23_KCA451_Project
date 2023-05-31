import React from "react";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";

const Subject = () => {
  const navigate = useNavigate();

  const subjectCodeRef = useRef("");
  const subjectNameRef = useRef("");

  async function submitHandler(event) {
    event.preventDefault();
    const studentData = {
      subjectCode: subjectCodeRef.current.value,
      subjectName: subjectNameRef.current.value,
    };
    const response = await fetch("http://localhost:8080/subject/addSubject", {
      method: "POST",
      body: JSON.stringify(studentData),
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (response.ok) {
      navigate("/addSubject");

      return response.json();
    }
  }
  return (
    <div>
      <React.Fragment>
        <AdminDash />
        <Header>
          <div className="container">
            <div className="col-md-9">
              <form onSubmit={submitHandler}>
                <div className="row">
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Subject Code</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={subjectCodeRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Subject Name</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={subjectNameRef}
                      />
                    </div>
                  </div>

                  <div className="col-md-12">
                    <div className="mb-3">
                      <label className="form-lable"></label>
                      <button type="submit" className="btn btn-success btn-lg">
                        Submit
                      </button>
                    </div>
                  </div>
                </div>
              </form>
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
  top: 30%;
`;

export default Subject;
