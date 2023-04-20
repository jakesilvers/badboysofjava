<template>
    <div>
        <Navbar />
        <div class="container mt-2">
            <invite />
            <div class="row">
                <div class="col-lg-6 col-md-12 mt-2">
                    <my-leagues />
                </div>
                <div class="col-lg-6 col-md-12 mt-2">
                    <my-matches />
                </div>
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
