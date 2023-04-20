<template>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <form @submit.prevent="login">
                <img src="logo.png" alt="" class="mb-4" />
                <div role="alert" class="mb-4 bad-login text-danger" v-if="invalidCredentials">Invalid username and password!</div>
                <div class="thank-you mb-4" role="alert" v-if="this.$route.query.registration">Thank you for registering, please sign in.</div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="username" v-model="user.username" required autofocus />
                    <label for="floatingInput">Username</label>
                </div>
                <div class="form-floating">
                    <input class="form-control" type="password" id="password" placeholder="password" v-model="user.password" required />
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                <p class="mt-2">
                    <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link>
                </p>
            </form>
        </main>
    </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: "login",
    components: {},
    data() {
        return {
            user: {
                username: "",
                password: ""
            },
            invalidCredentials: false
        };
    },
    methods: {
        login() {
            authService
                .login(this.user)
                .then((response) => {
                    if (response.status == 200) {
                        this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                        this.$store.commit("SET_USER", response.data.user);
                        this.$router.push("/");
                    }
                })
                .catch((error) => {
                    const response = error.response;

                    if (response.status === 401) {
                        this.invalidCredentials = true;
                    }
                });
        }
    }
};
</script>

<style scoped>
img {
    width: 100%;
}

a {
    color: #fefefe;
}
body {
    width: 100vw;
    height: 100vh;
    background-image: url(https://i.imgur.com/MnuytsD.jpg);
    background-size: cover;
    box-shadow: 0px 4px 4px 0px #00000040, inset 0 0 0 1000px rgba(0, 0, 0, 0.7);
}

body {
    padding-top: 40px;
    padding-bottom: 40px;
}

.thank-you {
    color: #4ade80;
}

button {
    background-color: #4ade80;
    border: 1px solid #22c55e;
}

button:hover {
    background-color: #16a34a;
    border: 1px solid #166534;
}

.form-signin {
    max-width: 330px;
    padding: 15px;
}

.fw-normal {
    font-weight: 400;
    color: white;
}
.form-signin input[type="text"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
}

.form-floating > .form-control,
.form-floating > .form-control-plaintext {
    padding: 1rem 0.75rem;
}

.form-floating > .form-control,
.form-floating > .form-control-plaintext,
.form-floating > .form-select {
    height: calc(3.5rem + calc(var(1px) * 2));
    line-height: 1.25;
}

.form-control {
    display: block;
    width: 100%;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #dee2e6;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.form-floating > label {
    display: none;
    top: 0;
    left: 0;
    z-index: 2;
    height: 100%;
    padding: 1rem 0.75rem;
    overflow: hidden;
    text-align: start;
    text-overflow: ellipsis;
    white-space: nowrap;
    pointer-events: none;
    border: 1px solid transparent;
    transform-origin: 0 0;
    transition: opacity 0.1s ease-in-out, transform 0.1s ease-in-out;
}

.form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
</style>
