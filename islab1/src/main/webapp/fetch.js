async function get(){
    let url = "api/controller";
    let response = await fetch(url);

    if(!response.ok){
        return "HTTP error" + response.status;
    }
    return await response.json();
}
