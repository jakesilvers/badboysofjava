<template>
    <div v-if="pendingInvitationsExist" class="row mb-2">
        <div class="col-6">
            <div v-for="invite in invitations" :key="invite.invitationID" v-show="invite.invitationStatus === 'Pending'" class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                            <h6 class="mb-0">{{ invite.leagueName }}</h6>
                            <p class="mt-0 mb-0">From: {{ invite.adminName }}</p>
                        </div>
                        <div class="col-2"><button @click="acceptInvite(invite)" class="btn btn-primary">Accept</button></div>
                        <div class="col-2"><button @click="rejectInvite(invite)" class="btn btn-danger">Reject</button></div>
                    </div>
                </div>
            </div>
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
    async created() {
        const id = this.$store.state.userID;
        try {
            const response = await axios.get(`/api/invitations/${id}`);

            console.log("invitations from API:", response.data);
            this.invitations = response.data;
            console.log("invitations in component:", this.invitations);
            for (const invitation of this.invitations) {
                if (invitation.invitationStatus === "Pending") {
                    const leagueResponse = await axios.get(`/api/league/${invitation.leagueID}`);
                    console.log("League name:", leagueResponse.data.leagueName);

                    this.$set(invitation, "leagueName", leagueResponse.data.leagueName);

                    const adminResponse = await axios.get(`/api/invitations/${invitation.invitationID}/admin`, {
                        headers: {
                            Authorization: `Bearer ${this.$store.state.token}`
                        }
                    });
                    console.log("Admin name:", adminResponse.data);

                    this.$set(invitation, "adminName", adminResponse.data);
                }
            }
        } catch (error) {
            console.error(error);
        }
    },

    methods: {
        acceptInvite(invite) {
            console.log("accepting invite:", invite);
            invite.invitationStatus = "Approved";
            axios.put(`/api/invitations/${invite.invitationID}`, invite).then((response) => {
                console.log("response:", response.data);
                location.reload();
                const index = this.invitations.findIndex((i) => i.invitationID === invite.invitationID);
                this.invitations.splice(index, 1);
            });
        },
        rejectInvite(invite) {
            invite.invitationStatus = "Rejected";
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
.btn-primary {
    background-color: #4ade80 !important;
    border: 1px solid #22c55e !important;
}

.btn-primary:hover,
.btn-primary:focus {
    background-color: #16a34a !important;
    border: 1px solid #166534 !important;
    cursor: pointer;
}
</style>
