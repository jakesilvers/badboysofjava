<template>
  <div>
    <form @submit.prevent="submitForm">
      <div class="mb-3">
        <label class="form-label" for="leagueName">League name</label>
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
        <label class="form-label" for="description">Description</label>
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

      <div class="mb-4">
        <label class="form-label" for="country">Course</label>
        <course-select-list
          :courses="courses"
          @course-selected="updateSelectedCourse"
        ></course-select-list>
      </div>
      <a id="text-link" href="#" class="text-primary d-block mb-3"
        ><strong>+</strong> Add new course</a
      >
      <a class="btn btn-primary d-block" type="submit">Create</a>
    </form>
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
