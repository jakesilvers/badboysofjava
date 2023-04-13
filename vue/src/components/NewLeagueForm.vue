<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1 class="mb-4">Create League</h1>

                <form @submit.prevent="submitForm">
                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="leagueName">League name</label>
                            <input v-model="leagueName" type="text" class="form-control" id="leagueName" placeholder="" value="" required />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="description">Description</label>
                            <input v-model="description" type="text" class="form-control" id="description" placeholder="" value="" required />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="country">Course</label>
                            <course-select-list :courses="courses" @course-selected="updateSelectedCourse"></course-select-list>
                        </div>
                    </div>
                    <button class="btn btn-primary btn block" type="submit">Create</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import CourseSelectList from "../components/CourseSelectList.vue";
export default {
    components: {
        CourseSelectList
    },
    data() {
        return {
            courses: [],
            selectedCourse: "",
            leagueName: "",
            description: ""
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
                courseID: this.selectedCourse
            };
            axios
                .post("/api/league", formData, {
                    headers: {
                        Authorization: `Bearer ${this.$store.state.token}`
                    }
                })
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.error(error);
                });
        }
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
    }
};
</script>

<style scoped>
.container {
    background-color: white;
    margin-top: 20px;
    padding: 20px 20px;
    width: 500px;
    border-radius: 5px;
}
</style>
