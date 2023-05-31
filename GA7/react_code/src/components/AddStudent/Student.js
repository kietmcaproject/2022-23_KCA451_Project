import React from "react";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";

const Student = () => {
  const navigate = useNavigate();
  const emailRef = useRef("");
  const firstNameRef = useRef("");
  const lastNameRef = useRef("");
  const addressRef = useRef("");
  const phoneRef = useRef("");
  const yearRef = useRef("");
  const sectionRef = useRef("");
  const fatherNameRef = useRef("");
  const motherNameRef = useRef("");
  const mentorRef = useRef("");
  const roleRef = useRef("");
  const passwordRef = useRef("");
  const rollNumRef = useRef("");
  const alternateEmailRef = useRef("");
  const dobRef = useRef("");
  const branchRef = useRef("");
  const courseRef = useRef("");
  const semesterRef = useRef("");

  async function submitHandler(event) {
    event.preventDefault();
    const studentData = {
      email: emailRef.current.value,
      firstName: firstNameRef.current.value,
      lastName: lastNameRef.current.value,
      address: addressRef.current.value,
      phone: phoneRef.current.value,
      year: yearRef.current.value,
      section: sectionRef.current.value,
      fatherName: fatherNameRef.current.value,
      motherName: motherNameRef.current.value,
      mentor: mentorRef.current.value,
      role: roleRef.current.value,
      password: passwordRef.current.value,
      rollNum: rollNumRef.current.value,
      alternateEmail: alternateEmailRef.current.value,
      dob: dobRef.current.value,
      branch: branchRef.current.value,
      course: courseRef.current.value,
      semester: semesterRef.current.value,
    };
    const response = await fetch("http://localhost:8080/student/addStudent", {
      method: "POST",
      body: JSON.stringify(studentData),
      headers: {
        "Content-Type": "application/json",
        " charset": "utf-8",
      },
    });
    if (response.ok) {
      navigate("/student");

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
                      <label className="form-lable">First Name</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={firstNameRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Last Name</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={lastNameRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Date of birth</label>
                      <input
                        className="form-control"
                        type="text"
                        ref={dobRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Course</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={courseRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Branch</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={branchRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Semester</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={semesterRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Year</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={yearRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Section</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={sectionRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Roll Number</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={rollNumRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Mentor</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={mentorRef}
                      />
                    </div>
                  </div>

                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Father Name</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={fatherNameRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Mother Name</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={motherNameRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Phone Number</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={phoneRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Email</label>
                      <input
                        type="text"
                        name="email"
                        className="form-control"
                        ref={emailRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Password</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={passwordRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Alternate Email</label>
                      <input
                        type="text"
                        name="phone"
                        className="form-control"
                        ref={alternateEmailRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Address</label>
                      <input
                        type="text"
                        name="address"
                        className="form-control"
                        ref={addressRef}
                      />
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="mb-3">
                      <label className="form-lable">Role</label>
                      <input
                        type="text"
                        name="username"
                        className="form-control"
                        ref={roleRef}
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
  top: 10%;
`;

export default Student;
