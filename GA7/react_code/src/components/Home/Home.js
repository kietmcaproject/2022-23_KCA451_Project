import React, { useRef } from "react";
import "./Home.css";
import styled from "styled-components";
import Bg from "../../assets/images/Bg.png";
import swal from "sweetalert";

import { useNavigate } from "react-router-dom";

const LoginPAge = styled.div``;
const Container = styled.div`
  display: flex;
  justify-content: space-evenly;
  align-items: center;
`;
const LoginLeft = styled.div`
  background: url(${Bg});
  background-size: cover;
  width: 70%;
  padding: 4%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const LoginLeftContents = styled.div``;
const Title = styled.h3`
  color: #fff;
  font-weight: 700;
  font-size: 45px;
  margin-bottom: 10px;
`;
const Subtitle = styled.h4`
  color: #fff;
`;
const LeftButton = styled.a`
  background-color: #0575e6;
  padding: 8px 24px;
  color: #fff;
  border-radius: 12px;
`;
const LoginRight = styled.div`
  width: 30%;
  padding: 4%;
`;
const LoginContents = styled.div``;
const RIghtTitle = styled.h1`
  font-size: 45px;
  color: #0575e6;
  margin-bottom: 60px;
  margin-left: 80px;
`;
const BoldText = styled.b`
  font-size: 22px;
  color: #585858;
`;
const RightSubtitle = styled.div``;

const Home = () => {
  const emailRef = useRef("");
  const passwordRef = useRef("");
  const navigate = useNavigate();

  async function submitHandler(event) {
    event.preventDefault();
    const user = {
      email: emailRef.current.value,
      password: passwordRef.current.value,
    };

    const response = await fetch("http://localhost:8080/user/login", {
      method: "POST",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      swal({
        title: "Invalid Email or Password",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      });
    }
    if (emailRef.current.value === "admin@gmail.com") {
      navigate("/AdminDashboard");
    } else {
      navigate("/dashboard");
    }
  }

  return (
    <>
      <LoginPAge>
        <Container>
          <LoginLeft>
            <LoginLeftContents>
              <Title>Academic Evaluation System</Title>
              <Subtitle>
                A helping tool for both student and faculties📝📝
              </Subtitle>
              <LeftButton>ReadMore</LeftButton>
            </LoginLeftContents>
          </LoginLeft>
          <LoginRight>
            <LoginContents>
              <RIghtTitle>🧑🏻‍🏫</RIghtTitle>
              <BoldText>Hello👋👋 Welcome back!</BoldText>
              <RightSubtitle>Login to get started📝📝</RightSubtitle>
              <br />
              <form>
                <input
                  type="email"
                  id="email"
                  name="email"
                  required={true}
                  ref={emailRef}
                  placeholder="Email Address"
                />
                <input
                  type="password"
                  id="password"
                  name="password"
                  required={true}
                  ref={passwordRef}
                  placeholder="Password"
                />
                <br />
                <button
                  type="button"
                  className="button"
                  onClick={submitHandler}
                >
                  Login
                </button>
              </form>
            </LoginContents>
          </LoginRight>
        </Container>
      </LoginPAge>
    </>
  );
};
export default Home;
