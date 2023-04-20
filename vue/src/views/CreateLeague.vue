<template>
    <div>
        <Navbar />
        <div class="container mt-2">
            <div class="row">
                <div class="col-lg-6 col-md-12">
                    <new-league-form />
                </div>
            </div>
        </div>
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

<style>
.col-md-12 {
    margin-left: auto;
    margin-right: auto;
}
</style>
