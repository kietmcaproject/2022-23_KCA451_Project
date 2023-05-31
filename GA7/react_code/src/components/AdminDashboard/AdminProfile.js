import React, { useEffect, useState } from "react";
import styled from "styled-components";
import AdminDash from "./AdminDash";

const url = "http://localhost:8080/findAdmin/admin@gmail.com";

function AdminProfile() {
  const [userData, setUserData] = useState({});

  useEffect(() => {
    getGitHubUserWithFetch();
  }, []);

  const getGitHubUserWithFetch = async () => {
    const response = await fetch(url);
    const jsonData = await response.json();
    setUserData(jsonData);
  };

  return (
    <div>
      <AdminDash />
      <Header>
        <div className="container">
          <div className="row gutters">
            <div className="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12"></div>
            <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
              <div className="card h-100">
                <div className="card-body">
                  <div className="row gutters">
                    <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"></div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="fullName">First Name</label>
                        <input
                          type="text"
                          className="form-control"
                          id="fullName"
                          value={userData.firstName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="eMail">LastName</label>
                        <input
                          type="email"
                          className="form-control"
                          id="fullName"
                          value={userData.lastName}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="phone">Email</label>
                        <input
                          type="text"
                          className="form-control"
                          id="eMail"
                          value={userData.email}
                        />
                      </div>
                    </div>
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                      <div className="form-group">
                        <label htmlFor="website">Phone</label>
                        <input
                          type="text"
                          className="form-control"
                          id="eMail"
                          value={userData.phone}
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Header>
    </div>
  );
}
const Header = styled.div`
  position: absolute;
  left: 20%;
  top: 20%;
`;

export default AdminProfile;
