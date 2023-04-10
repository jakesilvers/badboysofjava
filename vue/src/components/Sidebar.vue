<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="#"
                    >Home <span class="sr-only">(current)</span></a
                >
                <a class="nav-item nav-link" href="#">My Leagues</a>
                <a class="nav-item nav-link" href="#">My Tee Times</a>
                <a href="#" class="nav-item nav-link">
                    <router-link v-bind:to="{ name: 'create-league' }"
                        >Create League</router-link
                    ></a
                >
                <a class="nav-item nav-link" href="#">
                    <router-link
                        v-bind:to="{ name: 'logout' }"
                        v-if="$store.state.token != ''"
                        >Logout</router-link
                    ></a
                >
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
</style>
