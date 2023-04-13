<template>
    <div class="container mx-auto">
        <div class="row">
            <div class="col-12">
                <h4>League Invites</h4>
            </div>
        </div>
        <div class="row invite">
            <div class="col-8">
                <h6 class="mb-0">Jake's New League @ Course Name</h6>
                <p class="mt-0 mb-0">From: username</p>
            </div>
            <div class="col-2"><button class="btn btn-primary">Accept</button></div>
            <div class="col-2"><button class="btn btn-danger">Reject</button></div>
        </div>
        <div class="row invite">
            <div class="col-8">
                <h6 class="mb-0">Jake's New League @ Course Name</h6>
                <p class="mt-0 mb-0">From: username</p>
            </div>
            <div class="col-2"><button class="btn btn-primary">Accept</button></div>
            <div class="col-2"><button class="btn btn-danger">Reject</button></div>
        </div>
        <div class="row invite">
            <div class="col-8">
                <h6 class="mb-0">Jake's New League @ Course Name</h6>
                <p class="mt-0 mb-0">From: username</p>
            </div>
            <div class="col-2"><button class="btn btn-primary">Accept</button></div>
            <div class="col-2"><button class="btn btn-danger">Reject</button></div>
        </div>
    </div>
</template>

<script></script>

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
