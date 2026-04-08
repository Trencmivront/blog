export const checkUserToken = () => {
    const jwtToken = localStorage.getItem("jwt_token");
    if(jwtToken){
        return jwtToken;
    }
    console.log("Token not found.");
    return false;
}

export const loadUser = async () => {

    const config = {
        method: "GET",
        headers: {
            "Content-Type": "Application/json",
            "Authorization": `Bearer ${checkUserToken()}`
        }
    }

    const response = await fetch("http://localhost:8080/user/load", config);
    const data = await response.json();
    if(!response.ok){
        document.getElementById("log_in_button").hidden = false;
        alert(response.message);
        return;
    }
    
    document.getElementById("log_in_button").hidden = true;
    document.getElementById("link_to_index").textContent += data.username;

}