<template>
    <!-- <div class="home">
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
    </div> -->

    <div>
        <Navbar />
        <div class="container mt-2">
            <invite />
            <div class="row">
                <my-leagues />
                <my-matches />
            </div>
        </div>
    </div>
</template>

<script>
import MyMatches from "../components/MyMatches.vue";
import MyLeagues from "../components/MyLeagues.vue";
import Navbar from "../components/Navbar.vue";
import Invite from "../components/Invite.vue";
export default {
    components: { Navbar, MyMatches, MyLeagues, Invite },
    name: "home"
};
</script>

<style scoped></style>
