<template>
    <div class="container mt-2">
        <div class="row">
            <div class="col-lg-6 col-md-12">
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
                                <p>{{ leagueName }}</p>
                            </div>
                            <div class="col-4">
                                <h5>Course:</h5>
                                <p>League</p>
                            </div>
                        </div>
                        <button @click="addBtn" v-if="addPlayerBtn" v-show="!isMatchFull" class="btn btn-primary">Add Player</button>
                        <label for="player_select"></label>
                        <form v-if="!addPlayerBtn" @submit.prevent="addPlayerToMatch">
                            <select class="mt-2 custom-select d-block w-100" name="username" id="username" v-model="selectedUser">
                                <option value="">Please select a user</option>
                                <option v-for="user in users" :value="user" :key="user.id">{{ user }}</option>
                            </select>
                            <button class="btn btn-primary mt-2" @click="submitScores" type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-lg-6 col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h1>Scorecard</h1>
                        <form @submit.prevent="updateScore(0, player1Score)">
                            <h4>{{ matchPlayers[0] }}</h4>
                            <input class="form-control w-25" placeholder="score" type="number" v-model="player1Score" />
                            <button class="btn btn-primary mt-2">Submit</button>
                        </form>
                        <form @submit.prevent="updateScore(1, player2Score)">
                            <h4 class="mt-4">{{ matchPlayers[1] }}</h4>
                            <input class="form-control w-25" placeholder="score" type="number" v-model="player2Score" />
                            <button class="btn btn-primary mt-2">Submit</button>
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
    data() {
        return {
            match: [],
            users: [],
            selectedUser: "",
            addPlayerBtn: true,
            matchPlayers: [],
            leagueName: "",
            player1Score: null,
            player2Score: null
        };
    },
    methods: {
        updateScore(playerIndex, scoreValue) {
            const matchID = this.$route.params.id;
            axios
                .get(`/match/${matchID}/scorecards`)
                .then((response) => {
                    const scorecardID = response.data[playerIndex].scoreCardID;
                    const playerID = response.data[playerIndex].playerID;
                    console.log(scorecardID, matchID, "player id ", playerID);

                    axios
                        .put(
                            `/api/scorecards/${scorecardID}`,
                            {
                                scoreValue: scoreValue,
                                playerID: playerID,
                                matchID: matchID
                            },
                            {
                                headers: {
                                    Authorization: `Bearer ${this.$store.state.token}`
                                }
                            }
                        )
                        .then((response) => {
                            console.log(response.data);
                            // Update the score in the data object based on the playerIndex
                            if (playerIndex === 0) {
                                this.player1Score = scoreValue;
                            } else if (playerIndex === 1) {
                                this.player2Score = scoreValue;
                            }
                            location.reload();
                        })
                        .catch((error) => {
                            console.error(error);
                        });
                })
                .catch((error) => {
                    console.error(error);
                });
        },

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
        // GET LEAGUE NAME
        fetchLeagueName(leagueID) {
            axios
                .get(`/api/league/${leagueID}`)
                .then((response) => {
                    this.leagueName = response.data.leagueName;
                    console.info("league id", response.data);
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        // ADD PLAYER TO MATCH
        addPlayerToMatch() {
            const matchID = this.$route.params.id;
            const userName = this.selectedUser;
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
                            console.info("Player added to match.");
                            console.info(response);
                            location.reload();
                        })
                        .catch((error) => {
                            console.error(error);
                        });
                })
                .catch((error) => {
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
        // Fetch the scorecard for player 1
        axios
            .get(`/match/${matchID}/scorecards`)
            .then((response) => {
                const scorecardID = response.data[0].scoreCardID;
                axios
                    .get(`/api/scorecards/${scorecardID}`, {
                        headers: {
                            Authorization: `Bearer ${this.$store.state.token}`
                        }
                    })
                    .then((response) => {
                        this.player1Score = response.data.scoreValue;
                    })
                    .catch((error) => {
                        console.error(error);
                    });
            })
            .catch((error) => {
                console.error(error);
            });

        // Fetch the scorecard for player 2
        axios
            .get(`/match/${matchID}/scorecards`)
            .then((response) => {
                const scorecardID = response.data[1].scoreCardID;
                axios
                    .get(`/api/scorecards/${scorecardID}`, {
                        headers: {
                            Authorization: `Bearer ${this.$store.state.token}`
                        }
                    })
                    .then((response) => {
                        this.player2Score = response.data.scoreValue;
                    })
                    .catch((error) => {
                        console.error(error);
                    });
            })
            .catch((error) => {
                console.error(error);
            });
        axios
            .get(`/api/match/${matchID}`)
            .then((response) => {
                this.match = response.data;

                // FETCH PLAYER IDS AND ADD THEM TO MATCHPLAYERS ARRAY
                axios
                    .get(`/api/match/${matchID}/user`)
                    .then((response) => {
                        this.matchPlayers = response.data;
                        console.info("data", response.data);
                    })
                    .catch((error) => {
                        console.error(error);
                    });

                // FETCH USERS FOR LEAGUE
                const leagueID = this.match.leagueID;
                this.fetchUsersForLeague(leagueID);

                // FETCH LEAGUE NAME
                this.fetchLeagueName(leagueID);
            })
            .catch((error) => {
                console.error(error);
            });
    },
    computed: {
        // CHECKS IF THERE'S AT MOST 2 PLAYERS IN THE MATCH
        isMatchFull() {
            return this.matchPlayers.length === 2;
        }
    }
};
</script>

<style scoped>
.container {
    max-width: 500px;
}

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
