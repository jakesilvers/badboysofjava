<template>
    <div class="card">
        <div class="card-body">
            <h1>Scoreboard</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Username</th>
                        <th scope="col">Wins</th>
                        <th scope="col">Losses</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="player in players" :key="player[0]">
                        <td>{{ player && player[0] ? player[0] : "" }}</td>
                        <td>{{ player && player[1] ? player[1] : "" }}</td>
                        <td>{{ player && player[2] ? player[2] : "" }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            players: []
        };
    },
    mounted() {
        this.fetchPlayers();
    },
    methods: {
        fetchPlayers() {
            const leagueID = this.$route.params.id;
            axios
                .get(`api/league/${leagueID}/record`)
                .then((response) => {
                    this.players = response.data;
                    console.log(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }
};
</script>

<style scoped></style>
