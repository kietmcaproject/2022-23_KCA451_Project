
import { AppBar, Toolbar, styled, Button } from '@mui/material'; 
import { Link } from 'react-router-dom';

import { useNavigate } from 'react-router-dom';


const Component = styled(AppBar)`
    background: #FFFFFF;
    color: black;
`;

const Container = styled(Toolbar)`
    justify-content: center;
    & > a {
        padding: 20px;
        color: #000;
        text-decoration: none;
        
    }
`;
const NavLink = styled(Link)`
  
  color: green;
  text-decoration: none;
  &:hover {
    color: white;
    background: #359381;
  }
`;

const Header = () => {

    const navigate = useNavigate();

    const logout = async () => navigate('/account');
        
    return (
        <Component>
            
            <Container>
                <NavLink to='/'>HOME</NavLink>
                <NavLink to='/about'>ABOUT</NavLink>
                <NavLink to='/contact'>CONTACT</NavLink>
                <NavLink to='/account'>LOGOUT</NavLink>
            </Container>
        </Component>
    )
}

export default Header;