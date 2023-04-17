<template>
    <div>
        <Navbar />
        <league-view />
    </div>
    
</template>

<script>
import Navbar from "../components/Navbar.vue";
import LeagueView from "../components/LeagueView.vue";
export default {
    components: {
        Navbar,
        LeagueView
    }
};
</script>

<style></style>
