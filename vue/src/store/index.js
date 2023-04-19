import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));
const currentUserId = localStorage.getItem("userID"); // Add this line
const currentUsername = localStorage.getItem("username");

if (currentToken != null) {
    axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
    state: {
        token: currentToken || "",
        user: currentUser || {},
        userID: currentUserId || null, // Update this line
        username: currentUsername || ""
    },
    mutations: {
        SET_AUTH_TOKEN(state, token) {
            state.token = token;
            localStorage.setItem("token", token);
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        },
        SET_USER(state, user) {
            state.user = user;
            localStorage.setItem("user", JSON.stringify(user));
            state.username = user.username;
            state.userID = user.id;
            console.info("you are", user.username);
            localStorage.setItem("username", user.username);
            localStorage.setItem("userID", user.id);
            console.info("User ID Set to", state.userID);
        },

        LOGOUT(state) {
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            state.token = "";
            state.user = {};
            state.userID = null;
            axios.defaults.headers.common = {};
        }
    }
});
