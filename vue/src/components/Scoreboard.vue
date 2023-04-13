<template>
    <div>
        <h1>Scoreboard</h1>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Rank</th>
                    <th scope="col">Username</th>
                    <th scope="col">Wins</th>
                    <th scope="col">Losses</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>nchesko</td>
                    <td>3</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>nchesko</td>
                    <td>3</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>nchesko</td>
                    <td>3</td>
                    <td>1</td>
                </tr>
                <tr>
                    <th scope="row">4</th>
                    <td>nchesko</td>
                    <td>3</td>
                    <td>1</td>
                </tr>
            </tbody>
        </table>
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
    margin-top: 10px;
    margin-bottom: 20px;
}

tr {
    border-bottom: 2px solid #e9ecef;
}
</style>
