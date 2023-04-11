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
                        <tr>
                            <th scope="row">1</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Otto</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Periopolis Country Club</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Pound Town Par 3</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Pound Town Par 3</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Pound Town Par 3</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <a href="#">
                                <td>BBOJ Super League</td>
                            </a>
                            <td>Pound Town Par 3</td>
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
