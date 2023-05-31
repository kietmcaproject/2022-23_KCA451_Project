import AdminDash from "../AdminDashboard/AdminDash";
import styled from "styled-components";

function AddRemark() {
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

export default AddRemark;
