import React, { Component } from 'react';
import axios from 'axios';
class Feedback extends Component {
    state = { 

      first:"",
              last:"",
              city:"",
              phone:"",
              email:"",
              feedback:"",
               }
    
    handleOnChange = (event) => {
                const { name, value } = event.target;
                this.setState({
                  [name]: value,
                });
                
              };
    prevent=(response)=>{
    response.preventDefault();
    const data=this.state;
    console.log(data)
    axios.post("http://127.0.0.1:8000/feedback",data).then(()=>{
      alert("success")
      
    }).catch((error)=>{
      alert(error)
    })
    alert("form submited");
  }   
    render() { 
        const {fi,la,ci,ph,em,fe}=this.state;
        return (<>
        
        <div className='App'>
    
            <div className='text-center flex flex-col justify-center items-center  border-gray-300 pt-24 pb-28'>
            <div className="block p-6 max-w-lg  bg-white rounded-lg border  border-gray-200 shadow-md  dark:bg-white dark:border-gray-200 dark:hover:border-lime-500 ">
          <form  method="POST" action="/feedback" className="w-full max-w-lg " name="feedback" onSubmit={this.prevent} >
  <div className="flex flex-wrap -mx-3 mb-6 ">
    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-xs font-bold mb-2" htmlFor="grid-first-name">
        First Name
      </label>
      <input className="appearance-none block w-full  text-lime-600 placeholder-lime-400 border hover:shadow-xl  rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-lime-400"  id="grid-first-name" type="text" placeholder='First Name' name="first" onChange={this.handleOnChange} value={fi} required/>     
    </div>
    <div className="w-full md:w-1/2 px-3">
      <label className="block uppercase tracking-wide text-lime-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
        Last Name
      </label>
      <input className="appearance-none block w-full hover:shadow-md  border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-lime-400 text-lime-600 placeholder-lime-400" id="grid-last-name" name="last" type="text" placeholder="Last Name" onChange={this.handleOnChange} value={la} required/>
    </div>
  </div>
  
  <div className="flex flex-wrap -mx-3 mb-2">
    <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide  text-lime-700 text-xs font-bold mb-2" htmlFor="grid-city">
        City
      </label>
      <input className="appearance-none block w-full hover:shadow-md  text-lime-600 placeholder-lime-400 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-lime-400" id="grid-city" name="city" type="text" onChange={this.handleOnChange} value={ci} placeholder="Ghaziabad" required/>
    </div>
    

    <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide  text-lime-700 text-xs font-bold mb-2" htmlFor="grid-phone">
        Phone no
      </label>
      <input className="appearance-none block w-full hover:shadow-md  text-lime-600 placeholder-lime-400  border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-lime-400" id="grid-phone" name="phone" type="tel" onChange={this.handleOnChange} value={ph} placeholder="+91##########" required/>
    </div>
        
    <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
      <label className="block uppercase tracking-wide text-lime-700 text-xs font-bold mb-2"  htmlFor="grid-email">
        Email
      </label>
      <input className="appearance-none block w-full  text-lime-600 placeholder-lime-400 hover:shadow-md border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-lime-400" id="grid-email" type="email" onChange={this.handleOnChange} value={em} name="email" placeholder="Email" required/>
    </div>
   </div>
  <br/>
  <label className="block uppercase tracking-wide  text-lime-700 text-xs font-bold mb-2 h-4" htmlFor="grid-feedback">
        Feedback
      </label>
  <textarea className="text-lime-600 placeholder-lime-400 selection: resize border rounded focus:outline-none hover:shadow-md focus:shadow-outline focus:border-lime-400 h-24 w-4/5" onChange={this.handleOnChange} value={fe} name="feedback" required id="grid-feedback"></textarea>
  <br/>
  <br/>
  <button className="bg-red-500 hover:bg-rose-400 text-gray-800 font-semibold py-2 px-4 border border-gray-400 rounded shadow" onClick={this.handleOnChange}>
  Submit
</button>


</form>
    </div>

</div>
        </div>
        </>);
    }
}
 
export default Feedback;