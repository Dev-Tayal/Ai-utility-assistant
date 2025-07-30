import React, {useState} from "react";

function ImageGenerator(){
    const[prompt ,setPrompt]= useState('');
    const[imageUrls, setImageUrls]= useState([]);
    
    const handleChange= (e)=>{
        setPrompt(e.target.value)
    };

    const generateImage= async()=>{
        try{
            const response= await fetch(`http://localhost:8080/generate-image?prompt=${prompt}`);
            const urls= await response.json();
            setImageUrls(prev => [...prev, ...urls]);
        }
        catch(error){
            console.error("Error genrating image:", error)
        }
    };

    return(
        <div>
            <h2 style={{color:"orange"}}>Image Generator</h2>
            <input type="text" value={prompt} 
            onChange= {handleChange}
            placeholder="Enter the prompt"/>
            <br></br>
            <button onClick={generateImage}> Generate </button>

            <div className='image-grid'>
                {imageUrls.map((url,index)=> (
                    <img key={index} src={url} alt={`image ${index}`}/>
                ))}

                {[...Array(4-imageUrls.length)].map((_,index)=>(
                    <div key={index+imageUrls.length} className="empty-image-slot">
                    </div>
                ))}
                
            </div>

        </div>
        
    )

}
export default ImageGenerator;