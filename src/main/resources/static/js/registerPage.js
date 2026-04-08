const submit_button = document.getElementById("submit_button");
const messageP = document.getElementById("message");

const messageScreen = (message) => {
    messageP.innerText = message;
    messageP.style.color = red;
    messageP.style.fontSize = 40;
}

submit_button.addEventListener("click", (event) => {
    const password = document.getElementById("password_textarea").value;
    const confirmPassword = document.getElementById("c_password_textarea").value;
    event.preventDefault();
    if (password !== confirmPassword) {
        event.preventDefault(); 
            messageScreen(data.message);
            console.error(data.message);
        return;
    }
    console.log("executing postData()");
    postData();
});

const postData = async () => {

    const userRegister = {
        name: document.getElementById("name_textarea").value,
        surname: document.getElementById("surname_textarea").value,
        username: document.getElementById("username_textarea").value,
        email: document.getElementById("email_textarea").value,
        password: document.getElementById("password_textarea").value
    }

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userRegister)
    }

    console.log("sending api request");

    try{
        const response = await fetch("http://localhost:8080/user/create", config)
        const data = await response.json();

        if(!response.ok){
            messageScreen(data.message);
            console.error(data.message);
            return;
        }

        window.location.href ="/logIn";
    }catch(error){
        messageScreen(error);
        console.error(error);
    }
}