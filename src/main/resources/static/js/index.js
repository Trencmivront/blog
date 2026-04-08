import { checkUserToken, loadUser } from "./exports/fncs.js";

const blogsArticle = document.getElementById("blogs");
const logInButton = document.getElementById("log_in_button");

window.addEventListener("load", () => {
    getAllBlogData();
    // if local storage has existing token, use it to get user credidentals
    if(checkUserToken()){
        loadUser();
    }
})

logInButton.addEventListener("click", () =>{
    
})

const getAllBlogData = async () => {

    try{
        const response = await fetch("http://localhost:8080/blogs/get_blog/all")
        if(!response.ok){
            alert("Failed to fetch blog data.");
            log(`Warn ${response.message}`);
            return;
        }
    const blogs = await response.json();
    // Create blogs on page
    blogs.forEach(blog => {
        blogsArticle.innerHTML += `<h3>${blog.header}</h3>
        <p>${blog.body}</p>
        <h6>${blog.author}</h6>
        <p>${blog.createDate}</p>`;
        blogsArticle.style.border = solid;
        // padding is inner space, margin is outer space
        blogsArticle.style.margin = 10;
    });
    }catch(error){
        console.log(error);
    }
}

const filterBlogs = () => {

}