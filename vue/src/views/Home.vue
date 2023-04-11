<template>
    <div class="home">
        <Sidebar />
        <my-leagues />
    </div>
</template>

<script>
import MyLeagues from "../components/MyLeagues.vue";
import Sidebar from "../components/Sidebar.vue";
export default {
    components: { Sidebar, MyLeagues },
    name: "home"
};
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";
</style>
