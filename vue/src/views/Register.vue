<template>
    <div id="register" class="text-center">
        <div class="container">
            <div class="row">
                <div class="col-12 image-div">
                    <img
                        class="d-block"
                        src="https://i.imgur.com/ffeaW6I.png"
                        alt=""
                    />
                </div>
                <div class="col-12">
                    <div
                        class="card login-box shadow p-3 mb-5 bg-white rounded"
                    >
                        <div class="card-body login-form">
                            <form @submit.prevent="register">
                                <h1 class="mb-4">Create</h1>
                                <div role="alert" v-if="registrationErrors">
                                    {{ registrationErrorMsg }}
                                </div>
                                <div class="form-input-group">
                                    <label
                                        class="form-control-label"
                                        for="username"
                                        >Username</label
                                    >
                                    <input
                                        class="form-control"
                                        type="text"
                                        id="username"
                                        v-model="user.username"
                                        required
                                        autofocus
                                    />
                                </div>
                                <div class="form-input-group">
                                    <label
                                        class="form-control-label"
                                        for="password"
                                        >Password</label
                                    >
                                    <input
                                        class="form-control"
                                        type="password"
                                        id="password"
                                        v-model="user.password"
                                        required
                                    />
                                </div>
                                <label
                                    class="form-control-label"
                                    for="confirmPassword"
                                    >Confirm Password</label
                                >
                                <div class="form-input-group">
                                    <input
                                        class="form-control"
                                        type="password"
                                        id="confirmPassword"
                                        v-model="user.confirmPassword"
                                        required
                                    />
                                    <div class="login-btn login-text"></div>
                                    <button
                                        class="btn btn-outline-primary"
                                        type="submit"
                                    >
                                        Create Account
                                    </button>
                                </div>
                                <p>
                                    <router-link :to="{ name: 'login' }"
                                        >Already have an account? Log
                                        in.</router-link
                                    >
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: "register",
    data() {
        return {
            user: {
                username: "",
                password: "",
                confirmPassword: "",
                role: "user"
            },
            registrationErrors: false,
            registrationErrorMsg: "There were problems registering this user."
        };
    },
    methods: {
        register() {
            if (this.user.password != this.user.confirmPassword) {
                this.registrationErrors = true;
                this.registrationErrorMsg =
                    "Password & Confirm Password do not match.";
            } else {
                authService
                    .register(this.user)
                    .then((response) => {
                        if (response.status == 201) {
                            this.$router.push({
                                path: "/login",
                                query: { registration: "success" }
                            });
                        }
                    })
                    .catch((error) => {
                        const response = error.response;
                        this.registrationErrors = true;
                        if (response.status === 400) {
                            this.registrationErrorMsg =
                                "Bad Request: Validation Errors";
                        }
                    });
            }
        },
        clearErrors() {
            this.registrationErrors = false;
            this.registrationErrorMsg =
                "There were problems registering this user.";
        }
    }
};
</script>

<style scoped>
.form-input-group {
    margin-bottom: 1rem;
}
label {
    margin-right: 0.5rem;
}
</style>
