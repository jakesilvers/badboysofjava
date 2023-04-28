<template>
  <div class="card">
    <div class="card-body">
      <h1 class="mb-4">Create League</h1>
      <form @submit.prevent="submitForm">
        <div class="mb-3">
          <label for="leagueName">League name</label>
          <input
            v-model="leagueName"
            type="text"
            class="form-control"
            id="leagueName"
            placeholder=""
            value=""
            required
          />
        </div>

        <div class="mb-3">
          <label for="description">Description</label>
          <input
            v-model="description"
            type="text"
            class="form-control"
            id="description"
            placeholder=""
            value=""
            required
          />
        </div>

        <div class="mb-3">
          <label for="country">Course</label>
          <course-select-list
            :courses="courses"
            @course-selected="updateSelectedCourse"
          ></course-select-list>
        </div>
        <button class="btn btn-primary btn block" type="submit">Create</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CourseSelectList from "../components/CourseSelectList.vue";
import router from "../router/index";
export default {
  components: {
    CourseSelectList,
  },
  data() {
    return {
      courses: [],
      selectedCourse: "",
      leagueName: "",
      description: "",
    };
  },
  methods: {
    updateSelectedCourse(courseID) {
      this.selectedCourse = courseID;
    },
    submitForm() {
      const formData = {
        leagueName: this.leagueName,
        description: this.description,
        courseID: this.selectedCourse,
      };
      axios
        .post("/api/league", formData, {
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
          console.info("This is the league ID", response.data.leagueID);
          router.push({
            name: "league",
            params: { id: response.data.leagueID },
          });
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    axios
      .get("/api/courses")
      .then((response) => {
        this.courses = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
};
</script>
