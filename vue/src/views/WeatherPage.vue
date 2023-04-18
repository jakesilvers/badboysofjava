<template>
  <div>
        <Navbar />
        <div></div>
        <current-weather />
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import CurrentWeather from '../components/CurrentWeather.vue';

export default {
  components: {
    Navbar,
    CurrentWeather
  }
};
</script>
<style
    CurrentWeathere>
@import "~bootstrap/dist/css/bootstrap.css";
</style>
