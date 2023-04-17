<template>
    <div>
        <Navbar />
        <new-league-form />
    </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import NewLeagueForm from "../components/NewLeagueForm.vue";
export default {
    components: {
        Navbar,
        NewLeagueForm
    }
};
</script>

<style></style>
