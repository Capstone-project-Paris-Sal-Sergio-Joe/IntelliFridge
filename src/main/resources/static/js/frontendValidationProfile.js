const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const validatedEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
const editForm = document.getElementById('editForm');
const phoneNumber = document.getElementById('phoneNumber');
const validatedPhoneNumber = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
const errorProfileForm = document.getElementById('errorProfileForm');
const backendError = document.getElementById('backendErrorProfileMessage');

editForm.addEventListener('submit', (e) => {
    let messages = [];

    if(email.value.match(validatedEmail)){

    } else {
        messages.push('This is not a valid email');
    }

    if(phoneNumber.value.match(validatedPhoneNumber)){

    } else if (phoneNumber.value === '') {

    } else if (phoneNumber.value === null) {

    } else {
        messages.push('This is not a valid phone number');
    }


    if(messages.length >0){
        e.preventDefault()
        errorProfileForm.innerText = messages.join(', ');
    }
    backendError.innerHTML = '';
})