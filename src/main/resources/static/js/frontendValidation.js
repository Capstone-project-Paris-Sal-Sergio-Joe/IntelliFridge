const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const passwordConfirm = document.getElementById('confirm');
const signUpForm = document.getElementById('signUpForm');
const errorSignUp = document.getElementById('errorSignUp');
const validatedEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)+$/;
const phoneNumber = document.getElementById('phoneNumber');
const validatedPhoneNumber = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
const backendError = document.getElementById('errorM');


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

    if (password.value !== passwordConfirm.value) {
        messages.push('Password and password confirmation are not the same');
    }

    if(phoneNumber.value.match(validatedPhoneNumber)){

    } else if (phoneNumber.value === '') {

    } else if (phoneNumber.value === null) {

    } else {
        messages.push('This is not a valid phone number');
    }

    if(messages.length >0){
        e.preventDefault()
        errorSignUp.innerText = messages.join(', ');
    }
    backendError.innerText = '';
})