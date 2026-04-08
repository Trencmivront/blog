const submit = document.getElementById("login_submit");

submit.addEventListener("click", (event)=>{
    event.preventDefault();
    getUserToken();
});

const getUserToken = async () => {

    try{
        const userLogInContainer = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    }

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(userLogInContainer)
    }

    const response = await fetch("http://localhost:8080/user/login", config);

    const data = await response.text();
    
    if(!response.ok){
        const info = document.getElementById("info_p");
        info.textContent = data;
        info.style.fontSize = "40px";
        return;
    }

    localStorage.setItem("jwt_token", data);
    window.location.href = "/";
    }catch(error){
        console.log(error);
    }

}