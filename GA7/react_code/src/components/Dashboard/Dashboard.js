import React from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";
import UserProfile from "../../assets/images/user.jpg";
import Board from "../../assets/images/dashboard.png";
import Exam from "../../assets/images/exam.png";
import Attendance from "../../assets/images/attendance.png";
import Feedback from "../../assets/images/feedback.png";
import Contacts from "../../assets/images/contacts.png";
import Assignment from "../../assets/images/assignments.png";
import Logout from "../../assets/images/logout.png";
import Report from "../../assets/images/report.png";

export default function Dashboard() {
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
              <UserNAme>Kiran Chauhan</UserNAme>
              <UserEmail>123@gmail.com</UserEmail>
            </UserRight>
          </User>
          <Navbar>
            <Items>
              <Item>
                <IconContainer>
                  <Icon src={Board} />
                </IconContainer>
                <IconTitle>Dashboard</IconTitle>
              </Item>
              <NavLink to="/internal">
                <Item>
                  <IconContainer>
                    <Icon src={Exam} />
                  </IconContainer>

                  <IconTitle>Internal Marks</IconTitle>
                </Item>
              </NavLink>
              <Item>
                <IconContainer>
                  <Icon src={Attendance} />
                </IconContainer>
                <IconTitle>Attendance</IconTitle>
              </Item>
              <Item>
                <IconContainer>
                  <Icon src={Assignment} />
                </IconContainer>
                <IconTitle>Assignment</IconTitle>
              </Item>
              <Item>
                <IconContainer>
                  <Icon src={Report} />
                </IconContainer>
                <IconTitle>Final Report</IconTitle>
              </Item>
              <Item>
                <IconContainer>
                  <Icon src={Feedback} />
                </IconContainer>
                <IconTitle>Remarks</IconTitle>
              </Item>
              <NavLink to="/contact">
                <Item>
                  <IconContainer>
                    <Icon src={Contacts} />
                  </IconContainer>
                  <IconTitle>Contacts</IconTitle>
                </Item>
              </NavLink>
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
  margin: 5px;
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
