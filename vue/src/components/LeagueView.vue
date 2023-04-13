<template>
    <div class="container mx-auto">
        <div class="row">
            <div class="col-6">
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
                <div><input v-model="username" class="form-control" type="text" placeholder="username" /></div>
                <button @click="invitePlayers" class="btn btn-primary">Invite Players</button>
            </div>
            <div class="col-6">
                <scoreboard />
            </div>
        </div>
        <div class="row matches div col-12">
            <league-matches />
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Scoreboard from "./Scoreboard.vue";
import LeagueMatches from "./LeagueMatches.vue";

export default {
    components: { Scoreboard, LeagueMatches },
    data() {
        return {
            league: {},
            courseName: "",
            users: [],
            username: ""
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
                console.log(error);
            });
        axios
            .get(`/api/league/${leagueID}/user`)
            .then((response) => {
                this.users = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
    },
    methods: {
        invitePlayers() {
            const leagueID = this.$route.params.id;
            const playerID = this.username;
            axios
                .get(`/api/user/${playerID}`)
                .then((response) => {
                    const playerID = response.data;
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
                            console.log(response.data);
                        })
                        .catch((error) => {
                            console.log(error);
                        });
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }
};
</script>

<style scoped>
.container {
    background-color: white;
    border-radius: 5px;
    margin-top: 20px;
    margin-bottom: 20px;
    width: 1000px;
}

.description {
    width: 475px;
}

h1 {
    margin-top: 10px;
    margin-bottom: 20px;
    width: 475px;
}

.table {
    width: 475px;
    max-width: 475px;
}

.course,
.players {
    margin-top: -10px;
}

h2 {
    margin-top: 40px;
    margin-bottom: 20px;
}

tr {
    border-bottom: 2px solid #e9ecef;
}

.form-control {
    width: 75%;
}
</style>
