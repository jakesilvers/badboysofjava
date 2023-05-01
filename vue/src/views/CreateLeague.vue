<template>
  <div>
    <Navbar />
    <section class="jumbotron jumbotron-fluid mb-4">
      <div class="container">
        <h1 class="jumbotron-title mb-0">Create a League</h1>
      </div>
    </section>
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
    NewLeagueForm,
  },
};
</script>

<style></style>
