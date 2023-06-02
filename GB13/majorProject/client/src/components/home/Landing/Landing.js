import React, { useState } from 'react';
import { Link, useSearchParams } from 'react-router-dom';
import '../Landing/Landing.css'
import img1 from '../../imagess/hill1.png';
import img2 from '../../imagess/hill2.png';
import img3 from '../../imagess/hill3.png';
import img4 from '../../imagess/hill4.png';
import img5 from '../../imagess/hill5.png';
import img6 from '../../imagess/leaf.png';
import img7 from '../../imagess/plant.png';
import img8 from '../../imagess/tree.png';
import Logo from '../../../../src/logo.png'


const Landing = () => {
    const [searchParams] = useSearchParams();
    const category = searchParams.get('category');
    const [offset, setOffset] = useState()

    const handleScroll = () => setOffset(window.pageYOffset)

    window.addEventListener('scroll', handleScroll);
    return (<>

        <div className="App">

            <div className="zoom" >
                <header className='outer'>
                    <h2 className='logo'>SVP</h2>
                    {/* <img src={Logo} className='logo' alt="" /> */}
                  
                </header>
                <div id='text' style={{ marginTop: (offset * 3.5) + 'px' }}>
                    <p className='stay-curious'>Stay Curious</p>
                    <p className='discover-stories'>Discover stories, thinking, and expertise <p>from writers on any topic.</p></p>


                    <Link to={`/create?category=${category || ''}`} style={{ textDecoration: 'none' }}>

                        <a id="btn" href="#" ><span>Create Blog!</span></a>
                    </Link>


                </div>
                <img src={img1} alt="" id='img1' style={{ top: (1.5 * offset) + 'px' }} />
                {/* <p className='stay-curious'>Stay Curious</p> */}
                <img src={img2} alt="" id='img2' />
                <img src={img3} alt="" id='img3' />
                <img src={img4} alt="" id='img4' style={{ left: (-1.5 * offset) + 'px' }} />
                <img src={img5} alt="" id='img5' style={{ left: (1.5 * offset) + 'px' }} />
                <img src={img6} alt="" id='1mg6' style={{ top: (-1.5 * offset) + 'px' }} />
                <img src={img7} alt="" id='img7' />
                <img src={img8} alt="" id='img8' />

            </div>

        </div>

    </>
    );
}



export default Landing;