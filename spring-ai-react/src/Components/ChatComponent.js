import React, { use, useState } from 'react';

function ChatComponent(){
    const[prompt, setPrompt]= useState('');
    const[chatResponse, setChatResponse]= useState('');
    const handleChange= (e)=>{
        setPrompt(e.target.value)
    }
    const chat= async()=>{
        try{
            const response= await fetch(`http://localhost:8080/ask-ai-options?prompt=${prompt}`);
            const reply= await response.text();
            setChatResponse(reply);
        }
        catch(error){
            console.log("Getting error ", error);
        }
    }
    return (
        <div>
            <h2 style={{color: 'orange'}}>Chat Componenet</h2>
            <input type= 'text' value={prompt}
            onChange={handleChange}
            placeHolder="What's on your mind ?"/>
            <br></br>
            <button onClick={chat}>Ask AI</button>

            <div className='output'>
                <p>{chatResponse}</p>
            </div>

        </div>
    )
}

export default ChatComponent;