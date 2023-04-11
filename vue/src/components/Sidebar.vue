<template>
    <nav class="navbar navbar-expand navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="#"> <router-link v-bind:to="{ name: 'home' }">Home</router-link></a>
                <a href="#" class="nav-item nav-link"> <router-link v-bind:to="{ name: 'create-league' }">Create League</router-link></a>
                <a href="#" class="nav-item nav-link"> <router-link v-bind:to="{ name: 'league' }">League View</router-link></a>

                <a class="nav-item nav-link" href="#"> <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></a>
            </div>
        </div>
    </nav>
</template>

<script>
export default {};
</script>

<style>
#sidebar {
    background-color: white;
}

.navbar-nav {
  flex-direction: row;
}
.navbar-nav .nav-item {
  padding-right: 0.5rem;
  padding-left: 0.5rem;
}
</style>
