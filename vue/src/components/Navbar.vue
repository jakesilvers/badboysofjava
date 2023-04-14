<template>
    <header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
        <router-link v-bind:to="{ name: 'home' }">
            <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#"><img src="logo.png" alt="" /></a>
        </router-link>

        <div class="navbar-nav">
            <div class="nav-item text-nowrap">
                <a class="nav-link px-3" href="#"> <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">LOG OUT</router-link></a>
            </div>
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
    width: 30%;
}
.navbar {
    background-color: #4ade80 !important;
}
</style>
