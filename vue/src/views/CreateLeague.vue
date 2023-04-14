<template>
    <div>
        <sidebar />
        <new-league-form />
    </div>
</template>

<script>
import Sidebar from "../components/Navbar.vue";
import NewLeagueForm from "../components/NewLeagueForm.vue";
export default {
    components: {
        Sidebar,
        NewLeagueForm
    }
};
</script>

<style></style>
