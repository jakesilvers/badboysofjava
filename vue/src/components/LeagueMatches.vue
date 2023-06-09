<template>
    <div class="card">
        <div class="card-body">
            <h1>Matches</h1>
            <button @click="createMatchBtn" class="btn btn-primary mt-2 mb-2" v-if="!createdMatch">Create Match</button>
            <form @submit.prevent="createMatch" v-if="createdMatch">
                <div class="row">
                    <div class="col-6">
                        <input id="date" class="mt-2 form-control" type="date" v-model="date" />
                        <label for="date"></label>
                    </div>
                    <div class="col-6">
                        <input id="time" class="mt-2 form-control" type="time" v-model="time" />
                        <label for="time"></label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-4">
                        <button class="btn btn-primary mb-4">Submit</button>
                    </div>
                </div>
            </form>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Player</th>
                        <th scope="col">Player</th>
                        <th scope="col">Date</th>
                        <th scope="col">View</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="match in formattedMatches" scope="row" v-bind:key="match.id">
                        <td>{{ matchPlayers[match.matchID][0] }}</td>
                        <td>{{ matchPlayers[match.matchID][1] }}</td>
                        <td>{{ match.formattedDate }} @ {{ match.formattedTime }}</td>
                        <td>
                            <router-link :to="{ name: 'match', params: { id: match.matchID } }">
                                <button class="btn btn-primary">View</button>
                            </router-link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
    data() {
        return {
            match: null,
            matches: [],
            createdMatch: false,
            date: "",
            time: "",
            matchPlayers: []
        };
    },
    created() {
        axios
            .get(`/api/league/${this.$route.params.id}/match`)
            .then((response) => {
                this.matches = response.data;
                console.info("Match data", response.data);
                // Fetch match players for each match
                for (let i = 0; i < this.matches.length; i++) {
                    const matchID = this.matches[i].matchID;
                    axios
                        .get(`/api/match/${matchID}/user`)
                        .then((response) => {
                            this.$set(this.matchPlayers, matchID, response.data);
                            console.info(response.data);
                        })
                        .catch((error) => {
                            console.error(error);
                        });
                }
            })
            .catch((error) => {
                console.error("Error retrieving matches: ", error);
            });
    },
    computed: {
        formattedMatches() {
            return this.matches.map((match) => {
                return {
                    ...match,
                    formattedDate: moment(match.startTime).format("MMMM D, YYYY"),
                    formattedTime: moment(match.startTime).format("h:mm A")
                };
            });
        }
    },
    methods: {
        createMatchBtn() {
            this.createdMatch = true;
        },
        createMatch() {
            const startTime = moment(`${this.date}T${this.time}`).format("YYYY-MM-DD HH:mm:ss");

            const requestData = {
                leagueID: this.$route.params.id,
                startTime: startTime,
                completed: false
            };

            axios
                .post("/api/match", requestData)
                .then((response) => {
                    this.match = response.data;
                    this.createdMatch = false;
                    const matchingMatch = this.matches.find((match) => match.startTime === this.match.startTime);
                    matchingMatch.matchID = this.match.matchID;
                })
                .catch((error) => {
                    console.error("Error creating match: ", error);
                });
        }
    }
};
</script>
<style scoped>
button {
    background-color: #166534;
    border: 1px solid #166534;
}

button:hover {
    background-color: #166534;
    border: 1px solid #166534;
    cursor: pointer;
}
</style>
