import React from "react";
import Dashboard from "../Dashboard/Dashboard";
import styled from "styled-components";
import "../Home/Home.css";
function Contact() {
  return (
    <div>
      <Dashboard />
      <Header>
        <div className="container min-vh=100">
          <div className="card ">
            <div className="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
              <form>
                <div className="mb-3">
                  <input
                    className="form-control"
                    type="text"
                    id="name"
                    placeholder="Name"
                    required
                  />
                </div>
                <br />
                <div className="mb-3">
                  <input
                    className="form-control"
                    type="email"
                    placeholder="Email"
                    id="email"
                    required
                  />
                </div>
                <div className="mb-3">
                  <textarea
                    className="form-control"
                    rows="10"
                    id="message"
                    placeholder="Enter your message..."
                    required
                  />
                </div>
                <div class="container text-center">
                  <button type="submit" className="button">
                    Send
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </Header>
    </div>
  );
}

const Header = styled.div`
  position: absolute;
  left: 40%;
  top: 20%;
`;
export default Contact;
