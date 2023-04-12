<template>
    <select class="custom-select d-block w-100" v-model="selectedCourse">
        <option v-for="course in courses" :key="course.courseID" :value="course.courseID">{{ course.courseName }} {{ course.courseID }}</option>
    </select>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            courses: [],
            selectedCourse: ""
        };
    },
    mounted() {
        axios.get("/api/courses").then((response) => {
            this.courses = response.data;
        });
    },
    watch: {
        selectedCourse() {
            // Emit the selected course ID to the parent component
            this.$emit("course-selected", this.selectedCourse);
        }
    }
};
</script>
