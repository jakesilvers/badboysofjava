<template>
    <div class="container mx-auto">
        <div class="row">
            <div class="col-8">
                <h1>My Leagues</h1>
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
                            <td>{{ league.leagueName }}</td>
                            <td>{{ league.courseName }}</td>
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
            leagues: []
        };
    },
    mounted() {
        axios
            .get("/api/league/user/4")
            .then((response) => {
                this.leagues = response.data;

                // Create an array of Promises for each league's course name
                const courseNamePromises = this.leagues.map(async (league) => {
                    try {
                        const response = await axios.get(`/api/course/league/${league.leagueID}`);
                        league.courseName = response.data;
                    } catch (error) {
                        console.log(error);
                    }
                });

                // Wait for all Promises to resolve before rendering the data
                Promise.all(courseNamePromises).then(() => {
                    this.leagues = [...this.leagues];
                });
            })
            .catch((error) => {
                console.log(error);
            });
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

h1 {
    margin-top: 20px;
    margin-bottom: 20px;
}

.table {
    width: 475px;
    max-width: 475px;
}

tr {
    border-bottom: 2px solid #e9ecef;
}
</style>
