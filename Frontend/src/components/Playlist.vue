<template>

  <div>

    <h1>My Playlist App 9000</h1>

    <div>
    <v-card class="mx-auto"
        width="400"
            color="#FF80AB"
            theme="dark"
    >
        <v-card-text class="text-body-2">
          <p class="font-weight-bold">{{ joke.question }}</p>
          <br />
          {{ joke.answer }}
      </v-card-text>
    </v-card>
    </div>

    <h3>Track hinzufügen</h3>

    <form @submit.prevent="addTrack()">

      <input v-model="newTrack.name" placeholder="Name"/>
      <input v-model="newTrack.artist" placeholder="Artist"/>
      <input v-model="newTrack.genre" placeholder="Genre"/>
      <input v-model="newTrack.album" placeholder="Album"/>
      <input v-model="newTrack.duration" placeholder="Duration"/>

      <button type="submit">Track hinzufügen</button>

    </form>

    <h3>Spotify Info.</h3>

    <div v-if="spotifyInfo" class="spotify-container">
      <img :src="this.spotifyInfo.imageURL" alt="Album cover" class="spotify-image">
      <a :href="this.spotifyInfo.openSpotifyURL" target="_blank" class="spotify-link">
        <button>▶ Play</button>
      </a>
    </div>

    <h3>Tracks</h3>

    <ul>

      <li v-for="track in tracks" :key="track.id">

        <input v-model="track.name" type="text"/>
        <input v-model="track.artist" type="text"/>
        <input v-model="track.genre" type="text"/>
        <input v-model="track.album" type="text"/>
        <input v-model="track.duration" type="number"/>

        <button @click="getSpotifyInfo(track)">Spotify-Informationen holen</button>

        <button @click="deleteTrack(track.id)">Löschen</button>

        <button @click="updateTrack(track)">Speichern</button>

      </li>

    </ul>


  </div>

</template>


<script>

import axios from "axios";


export default {

  data() {

    return {

      tracks: [],

      newTrack: {

        id: "",

        name: "",

        artist: "",

        age: "",

      },

      joke: {
        question: "",
        answer: ""
      },

      spotifyInfo: {},

      baseUrl: "http://localhost:8080/api/tracks",
      jokeUrl: "http://localhost:8080/api/jokes"

    };

  },

  methods: {
    getJoke() {
      axios.get(this.jokeUrl)
        .then(response => response.data)
        .then(response => (this.joke.question = response.setup, this.joke.answer = response.delivery))
        .catch(error => console.log(error));
    },


    async fetchTracks() {

      try {

        const response = await axios.get(this.baseUrl);

        this.tracks = response.data;

      } catch (error) {

        console.error(error);

      }

    },

    async addTrack() {

      try {

        await axios.post(this.baseUrl, this.newTrack);

        this.newTrack = {id: "", name: "", artist: "", age: ""};

        await this.fetchTracks();

      } catch (error) {

        console.error(error);

      }

    },

    async deleteTrack(id) {

      try {

        await axios.delete(`${this.baseUrl}/${id}`);

        await this.fetchTracks();

      } catch (error) {

        console.error(error);

      }

    },

    async updateTrack(track) {

      try {

        await axios.put(`${this.baseUrl}/${track.id}`, track);

        await this.fetchTracks();

      } catch (error) {

        console.error(error);

      }

    },

    async getSpotifyInfo(track) {
      try {
        let response = await axios.get(this.baseUrl + `/${track.id}/spotify-info`);

        let spotifyInfoResponse
        if (response.status === 200) {
          spotifyInfoResponse = response.data;
          console.log("Spotify info fetched:" + response.status + " " + response.statusText + " " + JSON. stringify(spotifyInfoResponse));

          // set state
          this.spotifyInfo = spotifyInfoResponse;

        } else {
          console.error('Error (not ok) getting Spotify info:', response.status, response.statusText, spotifyInfoResponse);
        }
      } catch (error) {
        console.error('Error getting Spotify info:', error);
      }
    },

  },
  beforeMount() {
    this.getJoke();
  },

  created() {

    this.fetchTracks();

    console.log("Track component was loaded!!")

  },

};

</script>


<style scoped>


ul {

  list-style: none;

  padding: 0;

  max-width: 800px;

  margin: 0 auto;

}


li {

  margin-bottom: 1em;

  background: #f9f9f9;

  border: 1px solid #ccc;

  border-radius: 5px;

  padding: 1em;

  display: flex;

  flex-direction: column;

  align-items: flex-start;

  box-shadow: 0 10px 5px rgba(0, 0, 0, 0.1);

}


input {

  margin-right: 1em;

  padding: 0.25em;

  border-radius: 5px;

  border: 1px solid #ccc;

  margin-bottom: 1em;

}


button {

  padding: 0.5em 1em;

  border-radius: 5px;

  border: none;

  color: white;

  cursor: pointer;

  margin-bottom: 1em;

}


button:disabled {

  background: #ccc;

  cursor: not-allowed;

}


form {

  margin-top: 2em;

  display: flex;

  flex-direction: column;

  align-items: flex-start;

  max-width: 800px;

  margin: 0 auto;

  padding: 1em;

  border: 1px solid #ccc;

  border-radius: 5px;

  box-shadow: 0 10px 5px rgba(0, 0, 0, 0.1);


  background: #f9f9f9;

}


form input {

  margin-bottom: 1em;

  width: 100%;

}


form button {

  align-self: flex-end;

  background: #007BFF;

}


form button:hover {

  background: #0056b3;

}


li button {

  background: #dc3545;

}


li button:hover {

  background: #bd2130;

}


li button:nth-of-type(2) {

  background: #28a745;

}


li button:nth-of-type(2):hover {

  background: #1e7e34;

}

.spotify-container {
  position: relative;
  width: 300px;  /* width of your image */
  height: 300px; /* height of your image */
}

.spotify-image {
  width: 100%;
  height: 100%;
}

.spotify-link {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

</style>
