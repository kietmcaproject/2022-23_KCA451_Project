import React, { Component } from 'react';
class ErrorPage extends Component {
    state = {  } 
    render() { 
        return (<>
        <div className='h-96 text-center'>
        
        <p className='text-green-700 mt-48 font-mono text-6xl'>Wrong URL</p>
        
        </div>
        </>);
    }
}
 
export default ErrorPage;