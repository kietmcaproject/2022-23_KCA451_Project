//import imge from './err1.jpg'
import axios from 'axios';
import React, { Component } from 'react';
    
class History extends Component {
    state={

      data:[],
      spinner:false
      ,
      error:false
    };
   
componentDidMount(){
  this.setState({spinner:true})
  axios.get("http://127.0.0.1:8000/history").then((response)=>{
          console.log(response.data.length)
          console.log(response.data[1])
          this.setState({data:response.data})
          this.setState({spinner:false})
                    
}).catch((error)=>{
  console.log(error)
  this.setState({error:true,
  spinner:false})

})
}
    
    handleOnChange = (event) => {
      const { name, value } = event.target;
      this.setState({
        [name]: value,
      });
      
    };
    
    render() { 
        
        return (
          
            <>
  <div className='text-center'>
  {this.state.error&&(
  <h1 className='text-red-500 text-3xl'>Error...</h1>)}
              {this.state.spinner&&(<h1 className='text-lime-500 text-3xl'>Loading....</h1>)}
            </div>          
            <div className="flex flex-col">
            
  <div className="overflow-x-auto sm:-mx-6 mb-64 lg:-mx-8 ">
    <div className="py-2 inline-block min-w-full sm:px-6 mb-48 lg:px-8">
      <div className="overflow-hidden">
        <table className="min-w-full">
          <thead className=" border-b-white border-b-4">
            <tr>
              
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
              N
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                P
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                K
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Temperature
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Humidity
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Ph
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Rainfall
              </th>
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Model
              </th>
              
              <th scope="col" className="text-sm font-medium text-green-900 px-6 py-4 text-center">
                Result
              </th>
              
          
            </tr>
            </thead>
          <tbody>
          
            {this.state.data.map((student) => (
              <tr className='border-b-4 border-b-white text-Lime-400' key={student.id}>
            
                
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.N}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.P}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.K}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.temperature}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.ph}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.humidity}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.rainfall}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.ml}</th>
                <th scope="row " className='text-md text-lime-600 font-mono px-6 py-4 whitespace-nowrap'>{student.result}</th>
                
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  </div>
  
</div>
            </>
        );
    }
}
 
export default History;