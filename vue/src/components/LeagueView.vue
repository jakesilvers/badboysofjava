<template>
    <div class="container mx-auto mt-2">
        <div class="row">
            <div class="col-6">
                <div class="card">
                    <league-info />
                </div>
            </div>
            <div class="col-6">
                <div class="card">
                    <scoreboard />
                </div>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-6">
                <div class="card">
                    <league-matches />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import LeagueInfo from "./LeagueInfo.vue";
import Scoreboard from "./Scoreboard.vue";
import LeagueMatches from "./LeagueMatches.vue";

export default {
    components: { Scoreboard, LeagueInfo, LeagueMatches },
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
                console.info(error);
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
                            console.info(error);
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
h1 {
    margin-top: 10px;
    margin-bottom: 20px;
    width: 475px;
}

input {
    margin-top: -20px;
}

.course,
.players {
    margin-top: -10px;
}

h2 {
    margin-top: 40px;
    margin-bottom: 20px;
}

.form-control {
    width: 75%;
}
</style>
