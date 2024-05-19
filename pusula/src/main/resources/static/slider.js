

const img_slider_elements=document.querySelectorAll(".img-caroussel");
const arrows_elts= document.querySelectorAll(".arrows i")
const round_elts=document.querySelectorAll(".round");



let current_img= 1;

round_elts.forEach((round_elt)=>{

    round_elt.addEventListener("click", change_img_slider)
})



arrows_elts.forEach((arrow)=>{
    arrow.addEventListener("click", change_img_slider)
    
})




function change_img_slider(e){


    let index_img_to_show=null


    if (e.currentTarget.id === "previous"){

        index_img_to_show= parseInt(current_img) - 1 < 1 ? img_slider_elements.length  :  parseInt(current_img) - 1
    }


    else if(e.currentTarget.classList.contains("round")){

        index_img_to_show=e.currentTarget.getAttribute("data-img")
    
    }

    else{

        index_img_to_show= parseInt(current_img) + 1 > img_slider_elements.length ? 1 :  parseInt(current_img) + 1

    }



    img_slider_elements.forEach((img)=>{

    
        img.classList.remove("active")

        
        if(img.getAttribute("data-img") == index_img_to_show){

            current_img= img.getAttribute("data-img") 

            img.classList.add("active")

        }


    })



    round_elts.forEach((round_elt)=>{

        round_elt.classList.remove("active")

        if(round_elt.getAttribute("data-img") == index_img_to_show){

            round_elt.classList.add("active")

        }

    })


}