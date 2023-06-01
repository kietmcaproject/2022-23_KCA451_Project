import React, { Component } from 'react';
import axios from 'axios';
//import imge  from './err1.jpg';
class Pridict extends Component {
    state = { N:"",
              P:"",
              K:"",
              temp:"",
              Ph:"",
              humidity:"",
              rain:"",
              classifier:"",
              result:"",
              spinner:false,
              error:false,
              acc:"",
              e:false,
              ml:""

               } 


              prevent=(response)=>{
                response.preventDefault();
                if(this.state.N!==""&&this.state.P!=="" && this.state.K!==""&&this.state.temp!==""&&this.state.Ph!==""&&this.state.humidity!==""&&this.state.rain!==""&&this.state.classifier!=="")
              {
                const data=this.state;
                this.setState({spinner:true})
                axios.post("http://127.0.0.1:8000/recommend",data).then((response)=>{
                this.setState({spinner:false}) 
                console.log(response.data)
                
                 this.setState({acc:response.data[0]})

                this.setState({result:response.data[1]})
                this.setState({ml:response.data[2]})
                

                console.log(response.data.RF)  
                this.setState({e:true})
                 
                }).catch((error)=>{
                  this.setState({spinner:false})
                  this.setState({error:true})
                  alert(error)
                })
                alert("form submited");
              }
            
            else
            {
              alert("enter all fields");
            }
          }
          
              
              handleOnChange = (event) => {
                const { name, value } = event.target;
                this.setState({
                  [name]: value,
                });
                
              };

    render() { 
               return (<div>

<div className='App'>
    
<div className='flex justify-center'> 
      <div className="mb-10 mt-10 p-6 max-w-5/6 w-6/12 dark:hover:border-lime-500  bg-white relative   rounded-lg border border-gray-200 shadow-lg dark:bg-white dark:border-white-700 text-left  text-red-500">
<div className='text-center'>

    <h1 className='font-serif max-w-5/6 text-6xl text-green-700' >
      Enter Details
      
      </h1> 
      {this.state.spinner&&(<h1 className='font-serif text-lime-500 text-3xl'>loading....</h1>)}
      {this.state.error&&(
  <h1 className='text-red-500 text-3xl'>Error...</h1>)}

      </div>
            
  <form className="text-center w-full max-w-6xl " onSubmit={this.prevent} >
  

  <div className="flex flex-wrap -mx-3 mb-6">
    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="N">
        N
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" name='N' onChange={this.handleOnChange} id="N" type="text" placeholder="-" required/>
      
    </div>
   
    <div className="w-full md:w-1/2 px-3">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="P">
        P
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="P" name='P' type="text" onChange={this.handleOnChange} placeholder="-" required/>
    </div>
  </div>




  <div className="flex flex-wrap -mx-3 mb-6">
    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="K">
        K
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="K" name='K' onChange={this.handleOnChange} type="text" placeholder="-" required/>
      
    </div>
    <div className="w-full md:w-1/2 px-3">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="temp">
        Temperature
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="temp" name='temp' type="text" onChange={this.handleOnChange} placeholder="-" required/>
    </div>
  </div>



  <div className="flex flex-wrap -mx-3 mb-6">
    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="humidity">
        Humidity
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="humidity" name='humidity' type="text" onChange={this.handleOnChange} placeholder="-" required/>
      
    </div>
    <div className="w-full md:w-1/2 px-3">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="PH">
      Ph
      </label>
      <input className="text-2xl appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="PH" name='Ph' type="text" onChange={this.handleOnChange} placeholder="-" required/>
    </div>
  </div>


  <div className="flex flex-wrap -mx-3 mb-6">
    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-2xl font-bold mb-2" htmlFor="rain">
        Rainfall
      </label>
      <input className="appearance-none text-center block w-full md:w-full text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black text-2xl" id="rain" name='rain' type="text" onChange={this.handleOnChange} placeholder="-" required/>
      
    </div>
    <div className="w-full md:w-1/2 px-3">
    <label className="block uppercase tracking-wide hover: text-lime-700 text-2xl font-bold mb-4" htmlFor="classifier">
      Classifier
</label>
<div className="relative">
<select name="classifier" className="appearance-none text-center block w-full md:w-full text-2xl text-black border hover:shadow-md  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 placeholder-black" id="cp" required onChange={this.handleOnChange}>
  <option>-</option>
  <option value="rf">Random Forest</option>
    <option value="knn">KNN</option>
  <option value="dt">Deccision Tree</option>
  
  
 
  </select>

</div>

    </div>
  </div>








<br/>




<div className='text-end'>
<button className="bg-red-500  hover:bg-red-800 text-gray-800 font-semibold py-4 px-16 border border-gray-400 rounded shadow">
Submit
</button>
</div>

</form>



<h1 className='text-2xl text-green-700 text-center'>Accuracy of { this.state.ml  } is <br/>{  this.state.acc}</h1>
<h1 className='text-2xl text-green-700 text-center'>
  Result is<br/>
{this.state.result}
</h1>


</div>

</div>
</div>
        </div>);
    }
}
 
export default Pridict;