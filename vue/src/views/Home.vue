<template>
    <div class="home">
        <Sidebar />
    </div>
</template>

<script>
import Sidebar from "../components/Sidebar.vue";
export default {
    components: { Sidebar },
    name: "home"
};
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";
</style>
