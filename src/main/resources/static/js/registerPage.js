const submit_button = document.getElementById("submit_button");

submit_button.addEventListener("click", (event) => {
    // Use document to find the specific input elements
    const password = document.getElementById("password_textarea").value;
    const confirmPassword = document.getElementById("c_password_textarea").value;
    event.preventDefault();
    // Compare the actual strings (values)
    if (password !== confirmPassword) {
        // Prevent the form from actually sending
        event.preventDefault(); 
        alert("Passwords are not same!");
        return;
    }
    console.log("executing postData()");
    postData();
});

// Use 'const' or 'function' to define your function properly
const postData = () => {

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
    fetch("http://localhost:8080/user/create", config)
    .then((response) => {
        const data = response.json();
        if(!response.ok){
            console.error(data.message);
        }
        return data;})
    .then((data) => {
        alert(data.message);
    })
    .catch(error => {
        alert(error.message);
    });

};
