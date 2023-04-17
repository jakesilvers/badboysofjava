<template>
    <div class="container mt-2">
        <div class="row">
            <div class="col-6">
                <div class="card">
                    <div class="card-body">
                        <h1 class="mb-4">{{ matchPlayers[0] }} vs {{ matchPlayers[1] }}</h1>
                        <div class="row">
                            <div class="col-4">
                                <h5>Date:</h5>
                                <p>{{ formatDate(match.startTime) }}</p>
                            </div>
                            <div class="col-4">
                                <h5>League:</h5>
                                <p>League</p>
                            </div>
                            <div class="col-4">
                                <h5>Course:</h5>
                                <p>League</p>
                            </div>
                        </div>
                        <button @click="addBtn" v-if="addPlayerBtn" class="btn btn-primary">Add Player</button>
                        <label for="player_select"></label>
                        <form v-if="!addPlayerBtn" @submit.prevent="addPlayerToMatch">
                            <select class="mt-2 custom-select d-block w-100" name="username" id="username" v-model="selectedUser">
                                <option value="">Please select a user</option>
                                <option v-for="user in users" :value="user" :key="user.id">{{ user }}</option>
                            </select>
                            <button class="btn btn-primary mt-2" type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    components: {},
    data() {
        return {
            match: null,
            users: [],
            selectedUser: "",
            addPlayerBtn: true,
            matchPlayers: []
        };
    },
    methods: {
        // FORMAT THE DATE
        formatDate(dateString) {
            const options = {
                year: "numeric",
                month: "long",
                day: "numeric",
                hour: "numeric",
                minute: "numeric"
            };
            const date = new Date(dateString);
            return date.toLocaleDateString("en-US", options);
        },
        // GET ALL USERS FOR DROPDOWN LIST
        fetchUsersForLeague(leagueID) {
            axios
                .get(`/api/league/${leagueID}/user`)
                .then((response) => {
                    this.users = response.data;
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        addPlayerToMatch() {
            const matchID = this.$route.params.id;
            const userName = this.selectedUser; // assuming the selectedUser is the username
            axios
                .get(`/api/user/${userName}`)
                .then((response) => {
                    const userID = response.data;
                    axios
                        .post(
                            `/api/match/${matchID}/user/${userID}`,
                            {},
                            {
                                headers: {
                                    Authorization: `Bearer ${this.$store.state.token}`
                                }
                            }
                        )
                        .then((response) => {
                            // handle success

                            console.log("Player added to match.");
                            console.log(response);
                        })
                        .catch((error) => {
                            // handle error
                            console.error(error);
                        });
                })
                .catch((error) => {
                    // handle error
                    console.error(error);
                });
        },
        // HIDE THE ADD PLAYER BUTTON
        addBtn() {
            this.addPlayerBtn = false;
        }
    },
    mounted() {
        const matchID = this.$route.params.id;
        axios
            .get(`/api/match/${matchID}`)
            .then((response) => {
                this.match = response.data;

                // FETCH PLAYER IDS AND ADD THEM TO MATCHPLAYERS ARRAY
                axios
                    .get(`/api/match/${matchID}/user`)
                    .then((response) => {
                        this.matchPlayers = response.data;
                        console.log("data", response.data);
                    })
                    .catch((error) => {
                        console.error(error);
                    });

                // Fetch users for league
                const leagueID = this.match.leagueID;
                this.fetchUsersForLeague(leagueID);
            })
            .catch((error) => {
                console.error(error);
            });
    }
};
</script>

<style scoped>
.container {
    max-width: 500px;
}

button {
    background-color: #4ade80;
    border: 1px solid #22c55e;
}

button:hover {
    background-color: #16a34a;
    border: 1px solid #166534;
    cursor: pointer;
}
</style>
