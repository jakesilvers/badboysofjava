<template>
    <div>
        <Navbar />
        <league-view />
    </div>
</template>

<script>
import LeagueView from "../components/LeagueView.vue";
import Navbar from "../components/Navbar.vue";
export default {
    components: {
        Navbar,
        LeagueView
    }
};
</script>

<style scoped></style>
