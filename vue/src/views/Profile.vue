<template>
    <div class="profile">
        <Sidebar />
        <edit-profile-form />
    </div>
</template>

<script>

import Sidebar from "../components/Navbar.vue";
import EditProfileForm from "../components/EditProfileForm.vue";
export default {
    components: { 
        Sidebar, 
        EditProfileForm },
    name: "profile"
};
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.css";
</style>
