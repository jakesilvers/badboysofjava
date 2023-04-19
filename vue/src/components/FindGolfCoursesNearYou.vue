<template>
  <div>
    <navbar />
    <body>
      <div class="container my-5">
        <div class="row">
            <div class="col">
                <div class="card">
                  <div class="card-header">
                    <h1 class="card-title">Find Golf Courses Near You</h1>
                  </div>
                  <div class="card-body">
                    <div class="form-group">
                      <label for="radiusInput">Search radius (miles):</label>
                      <input id="radiusInput" type="number" v-model="radiusMiles" min="1" max="50" class="form-control">
                      <button type="button" class="btn btn-primary my-2" @click="getGolfCourses">Find Golf Courses</button>
                    </div>
                    <ul v-for="(course, index) in golfCourses" :key="index">
                      <li><strong>{{ course.poi ? course.poi.name : 'Unknown' }}</strong></li>
                      <li>{{ course.address.freeformAddress }}</li>
                    <li>Distance: {{ (course.dist / 1609.34).toFixed(2) }} miles</li>
                        <button type="button" class="btn btn-secondary" @click="addCourse(course)">Add Course</button>
                    </ul>
                  </div>
                </div>
            </div>
        </div>
      </div>
  </body>
  </div>
</template>

<script>
import axios from "axios";
import Navbar from './Navbar.vue';
export default {
  components: {Navbar},
  data() {
    return {
      golfCourses: [],
      radiusMiles: 10,
    };
  },
  computed: {
    radiusMeters() {
      return this.radiusMiles * 1609.34;
    },
  },
  methods: {
    getGolfCourses() {
      navigator.geolocation.getCurrentPosition(position => {
        const options = { method: "GET", headers: { accept: "application/json" } };
        const apiUrl = `https://api.tomtom.com/search/2/search/golf%course.json?lat=${position.coords.latitude}&lon=${position.coords.longitude}&radius=${this.radiusMeters}&limit=50&key=n5h9dRKAzw4S9f0GFbdnsKYw6rbrib49`;

        fetch(apiUrl, options)
          .then((response) => response.json())
          .then((response) => {
            this.golfCourses = response.results;
          })
          .catch((err) => console.error(err));
      });
    },
  addCourse(course) {
  const address = course.address.freeformAddress.split(', ');
  const formData = {
    courseName: course.poi ? course.poi.name : 'Unknown',
    courseAddress: address[0],
    city: address[1],
    state: address[2],
    country: "United States",
    distance: (course.dist / 1609.34).toFixed(2)
  };
  axios.post('/api/course', formData)
    .then(response => {
      console.log('Course added:', response.data);
    })
    .catch(error => {
      console.error('Error adding course:', error);
    });
}}}
</script>