<template>
    <div id="login">
        <div class="container">
            <div class="row">
                <div class="col-12 image-div">
                    <img class="d-block" src="https://i.imgur.com/ffeaW6I.png" alt="" />
                </div>
                <div class="card login-box shadow p-3 mb-5 bg-white rounded">
                    <div class="card-body login-form">
                        <form @submit.prevent="login">
                            <div>
                                <h1 class="mb-4">Sign In</h1>
                            </div>
                            <div role="alert" class="bad-login" v-if="invalidCredentials">Invalid username and password!</div>
                            <div class="thank-you" role="alert" v-if="this.$route.query.registration">Thank you for registering, please sign in.</div>

                            <div class="form-input-group">
                                <label class="form-control-label" for="username">Username</label>
                                <input class="form-control mb-3" type="text" id="username" v-model="user.username" required autofocus />
                            </div>
                            <div class="form-input-group">
                                <label class="form-control-label" for="password">Password</label>
                                <input class="form-control" type="password" id="password" v-model="user.password" required />
                            </div>
                            <div class="login-btn login-text">
                                <button class="btn btn-outline-primary" type="submit">Sign in</button>
                            </div>
                            <p>
                                <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
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

<style>
.login-box {
    height: auto;
    text-align: left;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
    max-width: 400px;
}

html,
body {
    height: 100%;
    background: linear-gradient(0deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
        url("https://images.unsplash.com/photo-1538423070486-9292881ea10a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80");

    background-size: cover;
}

p {
    margin-top: 20px;
}

.btn {
    margin-top: 20px;
}

.image-div {
    display: flex;
    justify-content: center;
}

img {
    width: 300px;
}

.thank-you {
    margin-top: 30px;
    margin-bottom: 30px;
    color: green;
}

.bad-login {
    margin-top: 30px;
    margin-bottom: 30px;
    color: red;
}
</style>
