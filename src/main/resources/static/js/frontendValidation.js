const username = document.getElementById('username');
const password = document.getElementById('password');
const passwordConfirm = document.getElementById('confirm');
const signUpForm = document.getElementById('signUpForm');
const errorSignUp = document.getElementById('errorSignUp');
const backendError = document.getElementById('errorM');

signUpForm.addEventListener('submit',(e) =>{

    let messages = [];

    if(password.value.length <= 8){
        messages.push('Password must be longer than 8 characters');
    }

    if (password.value.length >= 20) {
        messages.push('Password must be less than 20 characters')
    }

    if (password.value !== passwordConfirm.value) {
        messages.push('Password and password confirmation are not the same');
    }

    if(messages.length >0){
        e.preventDefault()
        errorSignUp.innerText = messages.join(', ');
    }
    if(backendError != null){
    backendError.innerText = '';
    }
    setTimeout(()=> {
        $('#errorSignUp').fadeOut('slow');
    }, 8000 );

})