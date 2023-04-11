<template>
    <div>
        <sidebar />
        <league-view />
    </div>
</template>

<script>
import LeagueView from "../components/LeagueView.vue";
import Sidebar from "../components/Sidebar.vue";
export default {
    components: {
        LeagueView,
        Sidebar
    }
};

Sidebar;
</script>

<style></style>
