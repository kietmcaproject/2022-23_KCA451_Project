import React, { Component } from 'react';
import img from './crop.jpg';
import Feedback from './Feedback';
import Home from './Home';
import Pridict from './Pridict';

import History from './History.jsx';
import {BrowserRouter as Router
        ,Route,Routes,Link} from 'react-router-dom';
import ErrorPage from './ErrorPage';
class NavBar extends Component {
    state = {  } 
    // prevent=(event)=>{
    // event.preventDefault();
    // }
     
    render() { 
        return (<>
        
        <Router>
          
        <div className='App bg-lime-200'>
              <div className="flex flex-wrap py-2">
        <div className="w-full px-4">
          <nav className="relative flex flex-wrap items-center justify-between px-2 py-3 bg-green-700 rounded">
            <div className="container px-4 mx-auto flex flex-wrap items-center justify-between">
              <div className="w-full relative flex justify-between lg:w-auto px-4 lg:static lg:block lg:justify-start">
                <Link to={"/Home"}
                   
                     className="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white"
                  
                ><div className='object-cover h-10 w-10'>
                  <img src={img} alt="crop"/>
                </div>
                </Link>
                <button
                  className="text-white cursor-pointer text-xl leading-none px-3 py-1 border border-solid border-transparent rounded bg-transparent block lg:hidden outline-none focus:outline-none"
                  type="button"
                  >
                  <i className="fas fa-bars"></i>
                  
                </button>
              </div>
              <div
                
                id="example-navbar-info"
              >
                <ul className="flex flex-col lg:flex-row list-none lg:ml-auto">
                  <li className="nav-item">
                    <Link to="/Home" 
                      className="px-3 py-2 flex items-center text-xs uppercase font-bold leading-snug text-white hover:opacity-75"
                      
                    
                   >
                      Home
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="./Pridict" 

                      className="px-3 py-2 flex items-center text-xs uppercase font-bold leading-snug text-white hover:opacity-75"
                      
                    >
                      Recommend
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="./History"
                      className="px-3 py-2 flex items-center text-xs uppercase font-bold leading-snug text-white hover:opacity-75"
                      
                    >
                      History
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to="./Feedback"
                      className="px-3 py-2 flex items-center text-xs uppercase font-bold leading-snug text-white hover:opacity-75"
                      
                    >
                      FeedBack
                    </Link>
                  </li>
                  
                </ul>
              </div>
            </div>
          </nav>
        </div>
      </div>
     
      
      <Routes>
      <Route exact path='/' element ={<Home/>}></Route>
       <Route exact path='/Home' element ={<Home/>}></Route>
        <Route exact path='/FeedBack' element={<Feedback/>}></Route>
        <Route exact path='/Pridict' element={<Pridict/>}></Route>
        
        <Route exact path='/History' element={<History/>}></Route>
        <Route exact path='*' element={<ErrorPage/>}></Route>
      </Routes>
            </div>
            </Router>
</>            
           );
    }
}
 
export default NavBar;