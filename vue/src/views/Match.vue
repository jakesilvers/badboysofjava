<template>
  <div>
    <navbar />
    <div class="container mt-2">
      <Matchview />
    </div>
  </div>
</template>

<script>
import Matchview from "../components/Matchview.vue";
import Navbar from "../components/Navbar.vue";
export default {
  components: {
    Matchview,
    Navbar,
  },
};
Navbar;
</script>

<style></style>
