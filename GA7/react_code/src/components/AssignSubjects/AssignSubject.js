import React, { useRef } from "react";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";
import { useNavigate, useParams } from "react-router-dom";
import swal from "sweetalert";

const AssignSubject = () => {
  const { email } = useParams();
  const subjectCodeRef = useRef("");
  const navigate = useNavigate();

  async function submitHandler() {
    const item = {
      email: email,
      subjectCode: subjectCodeRef.current.value,
    };
    await fetch("http://localhost:8080/student/assignSubject", {
      method: "PUT",
      body: JSON.stringify(item),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    }).then((res) => {
      if (!res.ok) {
        swal({
          title: `this is does not exist`,
          icon: "warning",
          buttons: true,
          dangerMode: true,
        });
      } else {
        console.log("successfull");
      }
    });
  }
  function handleBack() {
    navigate("/showSubject/" + email);
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
                      <label className="form-lable">Student Email</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        defaultValue={email}
                      />
                    </div>
                  </div>
                </div>
                <button className="button">Submit</button>
                <button className="button" onClick={handleBack}>
                  Back
                </button>
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
  top: 10%;
`;
export default AssignSubject;
