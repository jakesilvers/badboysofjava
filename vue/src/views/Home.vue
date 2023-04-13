<template>
    <div class="home">
        <Sidebar />

        <div class="container">
            <div class="row">
                <div class="col-6">
                    <my-leagues />
                </div>
                <div class="col-6">
                    <Invite />
                    <upcoming-matches />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import UpcomingMatches from "../components/UpcomingMatches.vue";
import MyLeagues from "../components/MyLeagues.vue";
import Sidebar from "../components/Sidebar.vue";
import Invite from "../components/Invite.vue";
export default {
    components: { Sidebar, MyLeagues, Invite, UpcomingMatches },
    name: "home"
};
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";
</style>
