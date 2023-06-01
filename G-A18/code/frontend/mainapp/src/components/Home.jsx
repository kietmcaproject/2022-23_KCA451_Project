//import { useNavigate } from 'react-router-dom';
//import pridict from '/pridict.jsx';
import img from './Modelcrop.jpg';

const Home=()=> {
    

    
        return (<>
        
        <div className='App'>
        <br/>
        <div className='flex justify-center'> 
      <div className="text-center p-6 max-w-5/6 w-4/5  bg-white relative   rounded-lg border border-gray-200 shadow-lg dark:bg-white dark:border-white-700 text-left  text-lime-700"><h1 className='text-begin font-bold text-3xl font-serif text-green-700' >Introduction</h1><br/><p className='font-serif'>Agriculture in India plays a predominant role in economy and employment. The common problem existing among the Indian farmers are they don't choose the right crop based on their soil requirements. Due to this they face a serious setback in productivity. This problem of the farmers has been addressed through precision agriculture. Precision agriculture is a modern farming technique that uses research data of soil characteristics, soil types, crop yield data collection and suggests the farmers the right crop based on their site-specific parameters. This reduces the wrong choice on a crop and increase in productivity.<br/>
        <br/>&emsp;&emsp;&emsp;&emsp;<span className='bg-lime-50 'href='/'>There are several factors that effect crops good growth we will use these parameters to recommend the crop <br/><li><ul>N - ratio of Nitrogen content in soil</ul>
        <ul>P - ratio of Phosphorous content in soil</ul>
        <ul>K - ratio of Potassium content in soil</ul>
        <ul>temperature - temperature in degree Celsius</ul>
        <ul>humidity - relative humidity</ul>
        <ul>ph - ph value of the soil</ul>
        <ul>rainfall - rainfall in mm</ul>
        </li></span>
        </p></div>
      </div>



 


      <br/>
      <br/>
          
      <br/>   
      <div className='flex justify-center'> 
      <div className="p-6 max-w-5/6 w-4/5  bg-white relative  mb-16  rounded-lg border border-gray-200 shadow-lg dark:bg-white dark:border-white-700 text-left  text-lime-700">
        <h1 className='text-center text-3xl text-green-700 font-serif font-bold'>Work flow of Model</h1>
        <img src={img} alt="model"/>
        </div>
      </div>
      
      </div>
      
        </>);
    }
  
 
export default Home;