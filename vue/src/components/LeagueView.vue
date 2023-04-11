<template>
    <div class="container mx-auto">
        <div class="row">
            <div class="col-8">
                <h1>BBOJ Super League</h1>
                <p class="description">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga at eum repellat accusantium nisi excepturi necessitatibus! Iste quidem non
                    quo, ab consequatur quibusdam nesciunt. Consequuntur, iure architecto. Facilis, placeat aliquam?
                </p>
                <p><strong>Course:</strong></p>
                <p class="course"><em>Periopolis Country Club</em></p>
                <p><strong>Players:</strong></p>
                <p class="players">nchesko, ssmith, jelsayed, abetzel, jsilvers</p>

                <h2>Scoreboard</h2>

                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Rank</th>
                            <th scope="col">Username</th>
                            <th scope="col">Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <a href="#">
                                <td>nchesko</td>
                            </a>
                            <td>68</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <a href="#">
                                <td>jsilvers</td>
                            </a>
                            <td>70</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <a href="#">
                                <td>ssmith</td>
                            </a>
                            <td>71</td>
                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <a href="#">
                                <td>abetzel</td>
                            </a>
                            <td>72</td>
                        </tr>
                        <tr>
                            <th scope="row">5</th>
                            <a href="#">
                                <td>jelsayed</td>
                            </a>
                            <td>74</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
    background-color: white;
    border-radius: 5px;
    margin-top: 20px;
    margin-bottom: 20px;
    width: 500px;
}

.description {
    width: 475px;
}

h1 {
    margin-top: 20px;
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
</style>
