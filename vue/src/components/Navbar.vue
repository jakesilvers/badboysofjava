<template>
    <header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
        <div>
            <router-link v-bind:to="{ name: 'home' }">
                <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#"><img src="/logo2.png" alt="" /></a>
            </router-link>
        </div>

        <div>
            <a class="golfCourses" href="#"> <router-link v-bind:to="{ name: 'golfCourses' }">FIND COURSES</router-link></a>
        </div>

        <div>
            <a class="weather" href="#"> <router-link v-bind:to="{ name: 'weather' }">WEATHER</router-link></a>
        </div>

        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="#"> <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">LOG OUT</router-link></a>
        </div>
    </header>
</template>

<script>
export default {};
</script>

<style scoped>
a {
    color: white !important;
}

img {
    max-width: 100px;
}

img:hover {
    background-color: rgb(54, 241, 54);
}
.navbar {
    background-color: green !important;
}
</style>
