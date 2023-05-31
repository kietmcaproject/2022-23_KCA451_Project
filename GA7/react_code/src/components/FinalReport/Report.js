import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";

function Report() {
  return (
    <div>
      <AdminDash />
      <Header>
        <h1>Hello</h1>
      </Header>
    </div>
  );
}
const Header = styled.div`
  position: absolute;
  left: 30%;
  top: 20%;
`;
export default Report;
