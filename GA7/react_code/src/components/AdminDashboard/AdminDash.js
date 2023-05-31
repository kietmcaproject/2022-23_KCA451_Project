import React from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";
import UserProfile from "../../assets/images/user.jpg";
import Board from "../../assets/images/dashboard.png";
import Exam from "../../assets/images/exam.png";
import Attendance from "../../assets/images/attendance.png";
import Feedback from "../../assets/images/feedback.png";
import Assignment from "../../assets/images/assignments.png";
import Logout from "../../assets/images/logout.png";
import Report from "../../assets/images/report.png";

export default function AdminDash() {
  return (
    <>
      <Fixed>
        <Aside>
          <DivTitle>
            <Text> KIET 🪁🪁</Text>
          </DivTitle>
          <User>
            <UserLeft>
              <ImageContainer>
                <UserImage src={UserProfile} />
              </ImageContainer>
            </UserLeft>
            <UserRight>
              <UserNAme>Admin</UserNAme>
              <UserEmail>admin@gmail.com</UserEmail>
            </UserRight>
          </User>
          <Navbar>
            <Items>
              <NavLink
                to="/AdminPro"
                style={({ isActive }) =>
                  isActive
                    ? {
                        color: "#fff",
                        background: "#7600dc",
                      }
                    : { color: "#545e6f", background: "#f0f0f0" }
                }
              >
                <Item>
                  <IconContainer>
                    <Icon src={Board} />
                  </IconContainer>
                  <IconTitle>Dashboard</IconTitle>
                </Item>
              </NavLink>

              <NavLink to="/student">
                <Item>
                  <IconContainer>
                    <Icon src={Attendance} />
                  </IconContainer>
                  <IconTitle>Add Student</IconTitle>
                </Item>
              </NavLink>

              <NavLink to="/addSubject">
                <Item>
                  <IconContainer>
                    <Icon src={Exam} />
                  </IconContainer>
                  <IconTitle>Add Subjects</IconTitle>
                </Item>
              </NavLink>

              <NavLink to="/show">
                <Item>
                  <IconContainer>
                    <Icon src={Exam} />
                  </IconContainer>
                  <IconTitle>Assign Subjects</IconTitle>
                </Item>
              </NavLink>
              <NavLink to="/showAttendance">
                <Item>
                  <IconContainer>
                    <Icon src={Attendance} />
                  </IconContainer>
                  <IconTitle>Mark Attendance</IconTitle>
                </Item>
              </NavLink>
              <NavLink to="/mark">
                <Item>
                  <IconContainer>
                    <Icon src={Assignment} />
                  </IconContainer>
                  <IconTitle>Mark Internals</IconTitle>
                </Item>
              </NavLink>
              <NavLink to="/report">
                <Item>
                  <IconContainer>
                    <Icon src={Report} />
                  </IconContainer>
                  <IconTitle>Final Report</IconTitle>
                </Item>
              </NavLink>
              <Item>
                <IconContainer>
                  <Icon src={Feedback} />
                </IconContainer>
                <IconTitle>Give Remarks</IconTitle>
              </Item>

              <NavLink to="/">
                <Item>
                  <IconContainer>
                    <Icon src={Logout} />
                  </IconContainer>
                  <IconTitle>LogOut</IconTitle>
                </Item>
              </NavLink>
            </Items>
          </Navbar>
        </Aside>
      </Fixed>
    </>
  );
}

const Fixed = styled.div`
  display: flex;
`;

const Aside = styled.aside`
  width: 20%;
  height: 100vh;
  box-shadow: 9px 6px 46px -24px rgba(0, 0, 0, 0.75);
`;

const DivTitle = styled.div`
  padding: 15px;
  border-bottom: 1px solid #c0c0c0;
`;
const Text = styled.h2`
  margin-left: 80px;
  color: #49b3f4;
  font-weight: bold;
`;
const User = styled.div`
  display: flex;
  margin-top: 70px;
  justify-content: center;
  margin-bottom: 2px;
`;
const UserLeft = styled.div`
  margin-right: 10px;
`;
const ImageContainer = styled.div``;
const UserImage = styled.img`
  border-radius: 50%;
`;
const Icon = styled.img`
  display: block;
  width: 100%;
`;
const IconContainer = styled.div`
  width: 10%;
  margin-right: 10px;
`;
const IconTitle = styled.a`
  color: #4e6481;
  font-weight: 600;
  cursor: pointer;
`;
const UserRight = styled.div``;
const UserNAme = styled.h4`
  margin: 0;
`;
const UserEmail = styled.small`
  color: #aca9a9;
`;
const Navbar = styled.div``;
const Items = styled.div`
  margin-top: 100px;
  margin-left: 30px;
`;
const Item = styled.div`
  display: flex;
  justify-content: start;
  margin-left: 20px;
  margin-bottom: 30px;
`;
