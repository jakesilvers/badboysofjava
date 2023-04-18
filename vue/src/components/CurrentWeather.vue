<template>
  <div>
    <navbar />
    <body>
      <div class="container my-5">
        <div class="row">
            <div class="col">
                      <img src="logo.png" alt="" >

                <div class="card">
                  <div class="card-header">
                    <h1 class="card-title">Current Weather</h1>
                  </div>
                  <div class="card-body">
                    <form @submit.prevent="getWeather">
                      <div class="form-group">
                        <label for="location">Location:</label>
                        <input type="text" id="location" v-model="searchLocation" required>
                      </div>
                      <button type="submit" class="btn btn-primary">Get Weather</button>
                    </form>
                    <p class="card-text">Location: {{ location }}</p>
                    <p class="card-text">Current Temperature: {{ temperature }}°F</p>
                    <p class="card-text">Feels like: {{ feelsLike }}°F</p>
                    <p class="card-text">Conditions: {{ conditions }}</p>
                    <p class="card-text">Humidity: {{ humidity }}%</p>
                    <p class="card-text">Wind Speed: {{ windSpeed }} mph</p>
                  </div>
                </div>
            </div>
        </div>
      </div>
  </body>
  </div>
</template>

<script>
import Navbar from './Navbar.vue';
export default {
  components: {Navbar  },
  data() {
    return {
      searchLocation: '',
      location: '',
      temperature: '',
      feelsLike: '',
      conditions: '',
      humidity: '',
      windSpeed: ''
    };
  },
  methods: {
    getWeather() {
      const options = { method: "GET", headers: { accept: "/application/json" } };
      const apiUrl = `https://api.tomorrow.io/v4/weather/realtime?location=${this.searchLocation}&units=imperial&apikey=MCD4c80qHB7bugs4Cgc6koJPE7yuQZYy`;

      fetch(apiUrl, options)
        .then((response) => response.json())
        .then((response) => {
          this.location = response.location.name + ", " + response.location.countryCode;
          this.temperature = response.data.values.temperature.toFixed(0);
          this.feelsLike = response.data.values.temperatureApparent.toFixed(0);
          this.conditions = response.data.values.weatherCode;
          this.humidity = response.data.values.humidity;
          this.windSpeed = response.data.values.windSpeed.toFixed(1);
        })
        .catch((err) => console.error(err));
    }
  }
};
</script>

<style scoped>
.container {
  margin-top: 500px;
   display: flex; 
   justify-content: center;
}
.card {
  border: 1px solid #ccc;
  border-radius: 4px;
}

.card-header {
  background-color: #f7f7f7;
  border-bottom: 1px solid #ccc;
}

.card-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 0;
}

.card-body {
  padding: 20px;
}

.card-text {
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}
button {
    background-color: #4ade80;
    border: 1px solid #22c55e;
}

button:hover {
    background-color: #16a34a;
    border: 1px solid #166534;
}
body {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url(https://images.pexels.com/photos/914682/pexels-photo-914682.jpeg);
  background-size: cover;
  box-shadow: 0px 4px 4px 0px #00000040, inset 0 0 0 1000px rgba(0, 0, 0, 0.7);
}
</style>