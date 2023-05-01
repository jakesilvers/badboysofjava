<template>
    <div>
        <navbar />
        <body>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h1 class="card-title">Current Weather</h1>
                            </div>
                            <div class="card-body">
                                <form @submit.prevent="getWeather">
                                    <div class="form-group">
                                        <label for="location">Location:</label>
                                        <input type="text" id="location" v-model="searchLocation" required />
                                    </div>
                                    <button type="submit" class="btn btn-primary">Get Weather</button>
                                </form>
                                <p class="card-text">Location: {{ location }}</p>
                                <p class="card-text">Current Temperature: {{ temperature }}°F</p>
                                <p class="card-text">Feels like: {{ feelsLike }}°F</p>
                                <p class="card-text">Conditions: {{ weatherConditions }}</p>
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
import Navbar from "./Navbar.vue";
export default {
    components: { Navbar },
    data() {
        return {
            searchLocation: "",
            location: "",
            temperature: "",
            feelsLike: "",
            weatherCode: "",
            humidity: "",
            windSpeed: ""
        };
    },
    computed: {
        weatherConditions() {
            const weatherCode = this.weatherCode;
            const weatherCodes = {
            
                    0: "Unknown",
                    1000: "Clear, Sunny",
                    1100: "Mostly Clear",
                    1101: "Partly Cloudy",
                    1102: "Mostly Cloudy",
                    1001: "Cloudy",
                    2000: "Fog",
                    2100: "Light Fog",
                    4000: "Drizzle",
                    4001: "Rain",
                    4200: "Light Rain",
                    4201: "Heavy Rain",
                    5000: "Snow",
                    5001: "Flurries",
                    5100: "Light Snow",
                    5101: "Heavy Snow",
                    6000: "Freezing Drizzle",
                    6001: "Freezing Rain",
                    6200: "Light Freezing Rain",
                    6201: "Heavy Freezing Rain",
                    7000: "Ice Pellets",
                    7101: "Heavy Ice Pellets",
                    7102: "Light Ice Pellets",
                    8000: "Thunderstorm",
                    2101: "Mostly Clear and Light Fog",
                    2102: "Partly Cloudy and Light Fog",
                    2103: "Mostly Cloudy and Light Fog",
                    2106: "Mostly Clear and Fog",
                    2107: "Partly Cloudy and Fog",
                    2108: "Mostly Cloudy and Fog",
                    4204: "Partly Cloudy and Drizzle",
                    4203: "Mostly Clear and Drizzle",
                    4205: "Mostly Cloudy and Drizzle",
                    4213: "Mostly Clear and Light Rain",
                    4214: "Partly Cloudy and Light Rain",
                    4215: "Mostly Cloudy and Light Rain",
                    4209: "Mostly Clear and Rain",
                    4208: "Partly Cloudy and Rain",
                    4210: "Mostly Cloudy and Rain",
                    4211: "Mostly Clear and Heavy Rain",
                    4202: "Partly Cloudy and Heavy Rain",
                    4212: "Mostly Cloudy and Heavy Rain",
                    5115: "Mostly Clear and Flurries",
                    5116: "Partly Cloudy and Flurries",
                    5117: "Mostly Cloudy and Flurries",
                    5102: "Mostly Clear and Light Snow",
                    5103: "Partly Cloudy and Light Snow",
                    5104: "Mostly Cloudy and Light Snow",
                    5122: "Drizzle and Light Snow",
                    5105: "Mostly Clear and Snow",
                    5106: "Partly Cloudy and Snow",
                    5107: "Mostly Cloudy and Snow",
                    5119: "Mostly Clear and Heavy Snow",
                    5120: "Partly Cloudy and Heavy Snow",
                    5121: "Mostly Cloudy and Heavy Snow",
                    5110: "Drizzle and Snow",
                    5108: "Rain and Snow",
                    5114: "Snow and Freezing Rain",
                    5112: "Snow and Ice Pellets",
                    6003: "Mostly Clear and Freezing drizzle",
                    6002: "Partly Cloudy and Freezing drizzle",
                    6004: "Mostly Cloudy and Freezing drizzle",
                    6204: "Drizzle and Freezing Drizzle",
                    6206: "Light Rain and Freezing Drizzle",
                    6205: "Mostly Clear and Light Freezing Rain",
                    6203: "Partly Cloudy and Light Freezing Rain",
                    6209: "Mostly Cloudy and Light Freezing Rain",
                    6213: "Mostly Clear and Freezing Rain",
                    6214: "Partly Cloudy and Freezing Rain",
                    6215: "Mostly Cloudy and Freezing Rain",
                    6212: "Drizzle and Freezing Rain",
                    6220: "Light Rain and Freezing Rain",
                    6222: "Rain and Freezing Rain",
                    6207: "Mostly Clear and Heavy Freezing Rain",
                    6202: "Partly Cloudy and Heavy Freezing Rain",
                    6208: "Mostly Cloudy and Heavy Freezing Rain",
                    7110: "Mostly Clear and Light Ice Pellets",
                    7111: "Partly Cloudy and Light Ice Pellets",
                    7112: "Mostly Cloudy and Light Ice Pellets",
                    7108: "Mostly Clear and Ice Pellets",
                    7107: "Partly Cloudy and Ice Pellets",
                    7109: "Mostly Cloudy and Ice Pellets",
                    7105: "Drizzle and Ice Pellets",
                    7106: "Freezing Rain and Ice Pellets",
                    7115: "Light Rain and Ice Pellets",
                    7117: "Rain and Ice Pellets",
                    7103: "Freezing Rain and Heavy Ice Pellets",
                    7113: "Mostly Clear and Heavy Ice Pellets",
                    7114: "Partly Cloudy and Heavy Ice Pellets",
                    7116: "Mostly Cloudy and Heavy Ice Pellets",
                    8001: "Mostly Clear and Thunderstorm",
                    8003: "Partly Cloudy and Thunderstorm",
                    8002: "Mostly Cloudy and Thunderstorm",
                    10000: "Clear, Sunny",
                    11000: "Mostly Clear",
                    11010: "Partly Cloudy",
                    11020: "Mostly Cloudy",
                    10010: "Cloudy",
                    11030: "Partly Cloudy and Mostly Clear",
                    21000: "Light Fog",
                    21010: "Mostly Clear and Light Fog",
                    21020: "Partly Cloudy and Light Fog",
                    21030: "Mostly Cloudy and Light Fog",
                    21060: "Mostly Clear and Fog",
                    21070: "Partly Cloudy and Fog",
                    21080: "Mostly Cloudy and Fog",
                    20000: "Fog",
                    42040: "Partly Cloudy and Drizzle",
                    42030: "Mostly Clear and Drizzle",
                    42050: "Mostly Cloudy and Drizzle",
                    40000: "Drizzle",
                    42000: "Light Rain",
                    42130: "Mostly Clear and Light Rain",
                    42140: "Partly Cloudy and Light Rain",
                    42150: "Mostly Cloudy and Light Rain",
                    42090: "Mostly Clear and Rain",
                    42080: "Partly Cloudy and Rain",
                    42100: "Mostly Cloudy and Rain",
                    40010: "Rain",
                    42110: "Mostly Clear and Heavy Rain",
                    42020: "Partly Cloudy and Heavy Rain",
                    42120: "Mostly Cloudy and Heavy Rain",
                    42010: "Heavy Rain",
                    51150: "Mostly Clear and Flurries",
                    51160: "Partly Cloudy and Flurries",
                    51170: "Mostly Cloudy and Flurries",
                    50010: "Flurries",
                    51000: "Light Snow",
                    51020: "Mostly Clear and Light Snow",
                    51030: "Partly Cloudy and Light Snow",
                    51040: "Mostly Cloudy and Light Snow",
                    51220: "Drizzle and Light Snow",
                    51050: "Mostly Clear and Snow",
                    51060: "Partly Cloudy and Snow",
                    51070: "Mostly Cloudy and Snow",
                    50000: "Snow",
                    51010: "Heavy Snow",
                    51190: "Mostly Clear and Heavy Snow",
                    51200: "Partly Cloudy and Heavy Snow",
                    51210: "Mostly Cloudy and Heavy Snow",
                    51100: "Drizzle and Snow",
                    51080: "Rain and Snow",
                    51140: "Snow and Freezing Rain",
                    51120: "Snow and Ice Pellets",
                    60000: "Freezing Drizzle",
                    60030: "Mostly Clear and Freezing drizzle",
                    60020: "Partly Cloudy and Freezing drizzle",
                    60040: "Mostly Cloudy and Freezing drizzle",
                    62040: "Drizzle and Freezing Drizzle",
                    62060: "Light Rain and Freezing Drizzle",
                    62050: "Mostly Clear and Light Freezing Rain",
                    62030: "Partly Cloudy and Light Freezing Rain",
                    62090: "Mostly Cloudy and Light Freezing Rain",
                    62000: "Light Freezing Rain",
                    62130: "Mostly Clear and Freezing Rain",
                    62140: "Partly Cloudy and Freezing Rain",
                    62150: "Mostly Cloudy and Freezing Rain",
                    60010: "Freezing Rain",
                    62120: "Drizzle and Freezing Rain",
                    62200: "Light Rain and Freezing Rain",
                    62220: "Rain and Freezing Rain",
                    62070: "Mostly Clear and Heavy Freezing Rain",
                    62020: "Partly Cloudy and Heavy Freezing Rain",
                    62080: "Mostly Cloudy and Heavy Freezing Rain",
                    62010: "Heavy Freezing Rain",
                    71100: "Mostly Clear and Light Ice Pellets",
                    71110: "Partly Cloudy and Light Ice Pellets",
                    71120: "Mostly Cloudy and Light Ice Pellets",
                    71020: "Light Ice Pellets",
                    71080: "Mostly Clear and Ice Pellets",
                    71070: "Partly Cloudy and Ice Pellets",
                    71090: "Mostly Cloudy and Ice Pellets",
                    70000: "Ice Pellets",
                    71050: "Drizzle and Ice Pellets",
                    71060: "Freezing Rain and Ice Pellets",
                    71150: "Light Rain and Ice Pellets",
                    71170: "Rain and Ice Pellets",
                    71030: "Freezing Rain and Heavy Ice Pellets",
                    71130: "Mostly Clear and Heavy Ice Pellets",
                    71140: "Partly Cloudy and Heavy Ice Pellets",
                    71160: "Mostly Cloudy and Heavy Ice Pellets",
                    71010: "Heavy Ice Pellets",
                    80010: "Mostly Clear and Thunderstorm",
                    80030: "Partly Cloudy and Thunderstorm",
                    80020: "Mostly Cloudy and Thunderstorm",
                    80000: "Thunderstorm",
                    10001: "Clear",
                    11001: "Mostly Clear",
                    11011: "Partly Cloudy",
                    11021: "Mostly Cloudy",
                    10011: "Cloudy",
                    11031: "Partly Cloudy and Mostly Clear",
                    21001: "Light Fog",
                    21011: "Mostly Clear and Light Fog",
                    21021: "Partly Cloudy and Light Fog",
                    21031: "Mostly Cloudy and Light Fog",
                    21061: "Mostly Clear and Fog",
                    21071: "Partly Cloudy and Fog",
                    21081: "Mostly Cloudy and Fog",
                    20001: "Fog",
                    42041: "Partly Cloudy and Drizzle",
                    42031: "Mostly Clear and Drizzle",
                    42051: "Mostly Cloudy and Drizzle",
                    40001: "Drizzle",
                    42001: "Light Rain",
                    42131: "Mostly Clear and Light Rain",
                    42141: "Partly Cloudy and Light Rain",
                    42151: "Mostly Cloudy and Light Rain",
                    42091: "Mostly Clear and Rain",
                    42081: "Partly Cloudy and Rain",
                    42101: "Mostly Cloudy and Rain",
                    40011: "Rain",
                    42111: "Mostly Clear and Heavy Rain",
                    42021: "Partly Cloudy and Heavy Rain",
                    42121: "Mostly Cloudy and Heavy Rain",
                    42011: "Heavy Rain",
                    51151: "Mostly Clear and Flurries",
                    51161: "Partly Cloudy and Flurries",
                    51171: "Mostly Cloudy and Flurries",
                    50011: "Flurries",
                    51001: "Light Snow",
                    51021: "Mostly Clear and Light Snow",
                    51031: "Partly Cloudy and Light Snow",
                    51041: "Mostly Cloudy and Light Snow",
                    51221: "Drizzle and Light Snow",
                    51051: "Mostly Clear and Snow",
                    51061: "Partly Cloudy and Snow",
                    51071: "Mostly Cloudy and Snow",
                    50001: "Snow",
                    51011: "Heavy Snow",
                    51191: "Mostly Clear and Heavy Snow",
                    51201: "Partly Cloudy and Heavy Snow",
                    51211: "Mostly Cloudy and Heavy Snow",
                    51101: "Drizzle and Snow",
                    51081: "Rain and Snow",
                    51141: "Snow and Freezing Rain",
                    51121: "Snow and Ice Pellets",
                    60001: "Freezing Drizzle",
                    60031: "Mostly Clear and Freezing drizzle",
                    60021: "Partly Cloudy and Freezing drizzle",
                    60041: "Mostly Cloudy and Freezing drizzle",
                    62041: "Drizzle and Freezing Drizzle",
                    62061: "Light Rain and Freezing Drizzle",
                    62051: "Mostly Clear and Light Freezing Rain",
                    62031: "Partly cloudy and Light Freezing Rain",
                    62091: "Mostly Cloudy and Light Freezing Rain",
                    62001: "Light Freezing Rain",
                    62131: "Mostly Clear and Freezing Rain",
                    62141: "Partly Cloudy and Freezing Rain",
                    62151: "Mostly Cloudy and Freezing Rain",
                    60011: "Freezing Rain",
                    62121: "Drizzle and Freezing Rain",
                    62201: "Light Rain and Freezing Rain",
                    62221: "Rain and Freezing Rain",
                    62071: "Mostly Clear and Heavy Freezing Rain",
                    62021: "Partly Cloudy and Heavy Freezing Rain",
                    62081: "Mostly Cloudy and Heavy Freezing Rain",
                    62011: "Heavy Freezing Rain",
                    71101: "Mostly Clear and Light Ice Pellets",
                    71111: "Partly Cloudy and Light Ice Pellets",
                    71121: "Mostly Cloudy and Light Ice Pellets",
                    71021: "Light Ice Pellets",
                    71081: "Mostly Clear and Ice Pellets",
                    71071: "Partly Cloudy and Ice Pellets",
                    71091: "Mostly Cloudy and Ice Pellets",
                    70001: "Ice Pellets",
                    71051: "Drizzle and Ice Pellets",
                    71061: "Freezing Rain and Ice Pellets",
                    71151: "Light Rain and Ice Pellets",
                    71171: "Rain and Ice Pellets",
                    71031: "Freezing Rain and Heavy Ice Pellets",
                    71131: "Mostly Clear and Heavy Ice Pellets",
                    71141: "Partly Cloudy and Heavy Ice Pellets",
                    71161: "Mostly Cloudy and Heavy Ice Pellets",
                    71011: "Heavy Ice Pellets",
                    80011: "Mostly Clear and Thunderstorm",
                    80031: "Partly Cloudy and Thunderstorm",
                    80021: "Mostly Cloudy and Thunderstorm",
                    80001: "Thunderstorm"
                
            };
            return weatherCodes[weatherCode] || "";
        }
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
                    this.weatherCode = response.data.values.weatherCodes;
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
    background-color: #166534;
    border: 1px solid #166534;
}

button:hover {
    background-color: #166534;
    border: 1px solid #166534;
    cursor: pointer;
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
