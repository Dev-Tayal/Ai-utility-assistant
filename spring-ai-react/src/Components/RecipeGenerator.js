import React, { use, useState } from 'react';

function RecipeGenerator(){
    const[ingredients, setIngredient]= useState('');
    const[cuisine,setCuisine]= useState('');
    const[restriction, setRestriction]= useState('');
    const[recipe, setRecipe]= useState('');

    const ingredientHandle= (e)=>{
        setIngredient(e.target.value);
    }
    const cuisinetHandle= (e)=>{
        setCuisine(e.target.value);
    }
    const restrictionHandle= (e)=>{
        setRestriction(e.target.value);
    }
    const createRecipe= async()=>{
        try{
            const response= await fetch(`http://localhost:8080/recipe-creator?ingredients=${ingredients}&cuisine=${cuisine}&dietaryRestriction=${restriction}`);
            const data = await response.text();
            setRecipe(data);
        }
        catch(error){
            console.log("Error generating recipe ",error);
        }
    }

    return(
        <div>
            <h2 style={{color:'orange'}}>Create A Recipe</h2>
            <input type='text' value={ingredients} 
            onChange={ingredientHandle}
            Placeholder='Enter Ingredients'></input>

            <input type='text' value={cuisine} 
            onChange={cuisinetHandle}
            Placeholder='Enter cuisine'></input>

            <input type='text' value={restriction} 
            onChange={restrictionHandle}
            Placeholder='Enter Dietary restriction'></input>

            <button className='button' onClick={createRecipe}>Generate</button>

            <div className='recipe-text'>
                <pre>{recipe}</pre>
            </div>
        </div>
    )
}

export default RecipeGenerator;