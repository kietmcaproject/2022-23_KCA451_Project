import React, { useEffect, useState, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";

const EditSubject = () => {
  const navigate = useNavigate();
  const { subjectCode } = useParams();
  const subjectNameRef = useRef("");

  const [subEdit, setSubEdit] = useState({
    subjectCode: subjectCode,
    subjectName: "",
  });

  useEffect(() => {
    const editSubject = async () => {
      const reqData = await fetch(
        "http://localhost:8080/subject/findSubject/" + subjectCode
      );
      setSubEdit(reqData);
    };
    editSubject();
  }, []);

  async function submitVal() {
    const item = {
      subjectName: subjectNameRef.current.value,
    };
    await fetch("http://localhost:8080/subject/updateSubject/" + subjectCode, {
      method: "PUT",
      body: JSON.stringify(item),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
  }
  function handleBack() {
    navigate("/addSubject");
  }

  return (
    <div>
      <AdminDash />
      <Header>
        <div className="container">
          <div className="col-md-9">
            <form onSubmit={submitVal}>
              <div className="row">
                <div className="col-md-6">
                  <h1>Edit subject</h1>
                  <div className="mb-3">
                    <label className="form-lable">Subject Code</label>
                    <input
                      type="text"
                      className="form-control"
                      defaultValue={subEdit.subjectCode}
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="mb-3">
                    <label className="form-lable">Subject Name</label>
                    <input
                      type="text"
                      className="form-control"
                      ref={subjectNameRef}
                      defaultValue={subEdit.subjectName}
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

                <div className="col-md-12">
                  <div className="mb-3">
                    <label className="form-lable"></label>
                    <button
                      type="submit"
                      className="btn btn-success btn-lg"
                      style={{ marginRight: "500%", marginTop: "3%" }}
                      onClick={handleBack}
                    >
                      Back
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </Header>
    </div>
  );
};

const Header = styled.div`
  position: absolute;
  left: 30%;
  top: 20%;
`;

export default EditSubject;
