<template>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">My Leagues</h5>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Location</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="league in leagues" :key="league.id">
                        <th scope="row">{{ league.leagueID }}</th>
                        <td>
                            <router-link :to="{ name: 'league', params: { id: league.leagueID } }">
                                {{ league.leagueName }}
                            </router-link>
                        </td>
                        <td>
                            {{ league.courseName }}
                        </td>
                    </tr>
                </tbody>
            </table>
            <button class="btn btn-primary">
                <router-link v-bind:to="{ name: 'create-league' }">Create New League</router-link>
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            leagues: []
        };
    },
    mounted() {
        axios
            .get(`/api/league/user/${this.$store.state.userID}`)
            .then((response) => {
                this.leagues = response.data;

                // Create an array of Promises for each league's course name
                const courseNamePromises = this.leagues.map(async (league) => {
                    try {
                        const response = await axios.get(`/api/course/league/${league.leagueID}`);
                        league.courseName = response.data;
                    } catch (error) {
                        console.info(error);
                    }
                });

                // Wait for all Promises to resolve before rendering the data
                Promise.all(courseNamePromises).then(() => {
                    this.leagues = [...this.leagues];
                });
            })
            .catch((error) => {
                console.info(error);
            });
    }
};
</script>

<style scoped>
button a {
    color: white !important;
    text-decoration: none;
}
td {
    color: black !important;
}

a {
    color: #16a34a;
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
