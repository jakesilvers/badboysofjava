<template>
    <div class="col-6">
        <div class="card">
            <div class="card-body">
                <h1>Matches</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th>League</th>
                            <th>Date</th>
                            <th>View</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="match in matches" :key="match.id">
                            <td>{{ match[1] }}</td>
                            <td>{{ formatDate(match[2]) }}</td>
                            <td>
                                <router-link :to="{ name: 'match', params: { id: match[3] } }">
                                    <button class="btn btn-primary">View</button>
                                </router-link>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            matches: []
        };
    },
    mounted() {
        axios
            .get(`/api/course/league/matches/user/${this.$store.state.userID}`)
            .then((response) => {
                this.matches = response.data;
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    },
    methods: {
        formatDate(dateTimeString) {
            const dateObj = new Date(dateTimeString);
            const options = {
                month: "long",
                day: "numeric",
                year: "numeric",
                hour: "numeric",
                minute: "numeric",
                hour12: true
            };
            return dateObj.toLocaleString("en-US", options);
        }
    }
};
</script>

<style scoped>
.container {
    background-color: white;
    border-radius: 5px;
    margin-top: 20px;
    width: 500px;
}

h4 {
    margin-top: 20px;
    margin-bottom: 5px;
}

tr {
    border-bottom: 2px solid #e9ecef;
}

.match {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
}

a {
    color: #16a34a;
}

button {
    background-color: #4ade80;
    border: 1px solid #22c55e;
}

button:hover {
    background-color: #16a34a;
    border: 1px solid #166534;
}
</style>
