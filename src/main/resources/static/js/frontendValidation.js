const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const signUpForm = document.getElementById('signUpForm');
const errorSignUp = document.getElementById('errorSignUp');
const validatedEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
const phoneNumber = document.getElementById('phoneNumber');
const validatedPhoneNumber = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

signUpForm.addEventListener('submit',(e) =>{
    let messages = [];


    if(email.value.match(validatedEmail)){

    } else {
        messages.push('This is not a valid email');
    }

    if(password.value.length <= 8){
        messages.push('Password must be longer than 8 characters');
    }

    if (password.value.length >= 20) {
        messages.push('Password must be less than 20 characters')
    }

    if(messages.length >0){
        e.preventDefault()
        errorSignUp.innerText = messages.join(', ');
    }
})


