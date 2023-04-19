<template>
    <div class="card-body">
        <h1>{{ league.leagueName }}</h1>
        <p class="description">
            {{ league.description }}
        </p>
        <p><strong>Course:</strong></p>
        <p class="course">
            <em>{{ courseName }}</em>
        </p>
        <p><strong>Players:</strong></p>
        <p class="players">
            <span v-for="user in users" :key="user.id"> {{ user }}</span>
        </p>
        <label for="username"></label>
        <p v-if="usernameError" class="text-danger mt-0 mb-2">{{ usernameError }}</p>
        <p v-if="inviteSent" class="text-success mt-0 mb-2">Invite Sent!</p>
        <div><input v-model="username" class="form-control" type="text" placeholder="username" /></div>
        <button @click="invitePlayers" class="btn btn-primary mt-2">Invite Players</button>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            league: {},
            courseName: "",
            users: [],
            username: "",
            inviteSent: false,
            usernameError: null
        };
    },
    mounted() {
        const leagueID = this.$route.params.id;
        axios
            .get(`/api/league/${leagueID}`)
            .then((response) => {
                this.league = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
        axios
            .get(`/api/course/league/${leagueID}`)
            .then((response) => {
                this.courseName = response.data;
            })
            .catch((error) => {
                console.info(error);
            });
        axios
            .get(`/api/league/${leagueID}/user`)
            .then((response) => {
                this.users = response.data;
            })
            .catch((error) => {
                console.info(error);
            });
    },
    methods: {
        invitePlayers() {
            if (!this.username) {
                this.usernameError = "Username is required!";
                this.username = "";
                return;
            }
            if (this.username === this.$store.state.username) {
                this.usernameError = "You cannot invite yourself!";
                this.username = "";
                return;
            }
            this.usernameError = null;
            const leagueID = this.$route.params.id;
            const username = this.username;

            axios
                .get(`/api/user/${username}`)
                .then((response) => {
                    const playerID = response.data;
                    if (this.users.includes(username)) {
                        this.usernameError = "This user is already in the league!";
                        this.username = "";
                        return;
                    }
                    axios
                        .post(
                            "/api/invitations",
                            { leagueID, playerID },
                            {
                                headers: {
                                    Authorization: `Bearer ${this.$store.state.token}`
                                }
                            }
                        )
                        .then((response) => {
                            console.info(response.data);
                            this.username = "";
                            this.inviteSent = true;
                            setTimeout(() => {
                                this.inviteSent = false;
                            }, 3000);
                        })
                        .catch((error) => {
                            console.log(error);
                        });
                })
                .catch(() => {
                    this.usernameError = "Username does not exist!";
                    this.username = "";
                });
        }
    }
};
</script>

<style scoped>
button {
    background-color: #4ade80 !important;
    border: 1px solid #22c55e !important;
}

button:hover,
button:focus {
    background-color: #16a34a !important;
    border: 1px solid #166534 !important;
    cursor: pointer;
}
</style>
