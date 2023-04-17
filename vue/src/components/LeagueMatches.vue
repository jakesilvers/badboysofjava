<template>
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
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Username</th>
                    <th scope="col">Date</th>
                    <th scope="col">View</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">4</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">5</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">6</th>
                    <td>nchesko</td>
                    <td>abetzel</td>
                    <td>1/23/2023</td>
                    <td>1</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
    data() {
        return {
            createdMatch: false,
            date: null,
            time: null
        };
    },
    methods: {
        createMatchBtn() {
            this.createdMatch = true;
        },
        createMatch() {
            const startTime = moment(`${this.date}T${this.time}`).format("YYYY-MM-DD HH:mm:ss");
            console.log(startTime);

            const requestData = {
                leagueID: 1,
                startTime: startTime,
                completed: false
            };

            axios
                .post("/api/match", requestData)
                .then((response) => {
                    console.log("Match created: ", response.data);
                    this.createdMatch = false;
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
