<template>
    <div id="app">
        <div id="nav">
            <!-- <router-link v-bind:to="{ name: 'home' }"
                ><h4>HOME</h4></router-link
            >
            <router-link
                v-bind:to="{ name: 'logout' }"
                v-if="$store.state.token != ''"
                >Logout</router-link
            > -->
        </div>
        <router-view />
    </div>
</template>

<style>
#nav {
    text-align: center;
    margin-top: 30px;
}

a {
    color: white;
}
</style>
