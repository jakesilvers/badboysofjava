<template>
  <div>
      <Navbar />
    <h1>Golf Courses Near You</h1>
    <find-golf-courses-near-you />
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import FindGolfCoursesNearYou from '../components/FindGolfCoursesNearYou.vue';

export default {
  components: {
    Navbar,
    FindGolfCoursesNearYou
  }
};
</script>,
    FindGolfCoursesNearYou