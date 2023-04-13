<template>
    <div v-if="pendingInvitationsExist" class="container mx-auto">
        <div class="row">
            <div class="col-12">
                <h4>League Invites</h4>
            </div>
        </div>
        <div v-for="invite in invitations" :key="invite.invitationID" v-show="invite.invitationStatus === 'Pending'" class="row invite">
            <div class="col-8">
                <h6 class="mb-0">{{ invite.leagueID }}</h6>
                <p class="mt-0 mb-0">From: username</p>
            </div>
            <div class="col-2"><button @click="acceptInvite(invite)" class="btn btn-primary">Accept</button></div>
            <div class="col-2"><button class="btn btn-danger">Reject</button></div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            invitations: []
        };
    },
    created() {
        const id = this.$store.state.userID;
        axios.get(`/api/invitations/${id}`).then((response) => {
            console.log("invitations from API:", response.data);
            this.invitations = response.data;
            console.log("invitations in component:", this.invitations);
        });
    },
    methods: {
        acceptInvite(invite) {
            console.log("accepting invite:", invite);
            invite.invitationStatus = "Approved";
            axios.put(`/api/invitations/${invite.invitationID}`, invite).then((response) => {
                console.log("response:", response.data);
                const index = this.invitations.findIndex((i) => i.invitationID === invite.invitationID);
                this.invitations.splice(index, 1);
            });
        }
    },
    computed: {
        pendingInvitationsExist() {
            return this.invitations.some((invite) => invite.invitationStatus === "Pending");
        }
    }
};
</script>

<style scoped>
.container {
    background-color: white;
    border-radius: 5px;
    margin-top: 20px;
    width: 500px;
}

h4 {
    margin-top: 20px;
    margin-bottom: 5px;
}

.table {
    width: 475px;
    max-width: 475px;
}

tr {
    border-bottom: 2px solid #e9ecef;
}

button {
    margin-bottom: 20px;
}

.invite {
    padding-right: 20px;
    display: flex;
    align-items: center;
    justify-content: space-evenly;
}
</style>
