<template>
    <div>
        <navbar />
        <div class="container mt-4">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-header">
                            <h1 class="card-title">Find Golf Courses Near You</h1>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="radiusInput">Search radius (miles):</label>
                                <input id="radiusInput" type="number" v-model="radiusMiles" min="1" max="50" class="form-control" />
                                <button type="button" class="btn btn-primary my-2" @click="getGolfCourses">Find Golf Courses</button>
                            </div>
                            <ul v-for="(course, index) in golfCourses" :key="index">
                                <li>
                                    <strong>{{ course.poi ? course.poi.name : "Unknown" }}</strong>
                                </li>
                                <li>{{ course.address.freeformAddress }}</li>
                                <li>Distance: {{ (course.dist / 1609.34).toFixed(2) }} miles</li>
                                <button type="button" class="btn btn-secondary" @click="addCourse(course)" :disabled="isCourseAdded(course)">Add Course</button>
                            </ul>
                            <div v-if="successMessage" class="alert alert-success mt-3">
                                {{ successMessage }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Navbar from "./Navbar.vue";
export default {
    components: { Navbar },
    data() {
        return {
            golfCourses: [],
            radiusMiles: 10,
            addedCourses: [],
            successMessage: ""
        };
    },
    computed: {
        radiusMeters() {
            return this.radiusMiles * 1609.34;
        }
    },
    methods: {
        getGolfCourses() {
            navigator.geolocation.getCurrentPosition((position) => {
                const options = {
                    method: "GET",
                    headers: { accept: "application/json" }
                };
                const apiUrl = `https://api.tomtom.com/search/2/search/golf%course.json?lat=${position.coords.latitude}&lon=${position.coords.longitude}&radius=${this.radiusMeters}&limit=50&key=n5h9dRKAzw4S9f0GFbdnsKYw6rbrib49`;

                fetch(apiUrl, options)
                    .then((response) => response.json())
                    .then((response) => {
                        this.golfCourses = response.results;
                    })
                    .catch((err) => console.error(err));
            });
        },
        isCourseAdded(course) {
            return this.addedCourses.some((c) => c.courseName === (course.poi ? course.poi.name : null));
        },
        addCourse(course) {
            if (this.isCourseAdded(course)) {
                this.successMessage = "This course has already been added to your list of Golf Courses.";
                return;
            }
            const address = course.address.freeformAddress.split(", ");
            const formData = {
                courseName: course.poi ? course.poi.name : "Unknown",
                address: address[0],
                city: address[1],
                state: address[2],
                country: "United States",
                distance: (course.dist / 1609.34).toFixed(2)
            };
            axios
                .post("/api/course", formData)
                .then((response) => {
                    alert("Course added:", response.data);
                    this.addedCourses.push(formData);
                    this.successMessage = "Course added to your list of Golf Courses.";
                })
                .catch((error) => {
                    console.error("Error adding course:", error);
                });
        }
    }
};
</script>
<style scoped>
body {
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url(https://i.imgur.com/MnuytsD.jpg);
    background-size: cover;
    box-shadow: 0px 4px 4px 0px #00000040, inset 0 0 0 1000px rgba(0, 0, 0, 0.7);
}

card {
    margin-top: 20px;
}

button {
    background-color: #166534;
    border: 1px solid #166534;
}

button:hover {
    background-color: #166534;
    border: 1px solid #166534;
    cursor: pointer;
}
</style>
