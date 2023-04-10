6
<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1 class="mb-4">Create League</h1>

                <form>
                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="leagueName">League name</label>
                            <input
                                type="text"
                                class="form-control"
                                id="leagueName"
                                placeholder=""
                                value=""
                                required
                            />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="leagueName">Description</label>
                            <input
                                type="text"
                                class="form-control"
                                id="description"
                                placeholder=""
                                value=""
                                required
                            />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="country">Course</label>
                            <select
                                class="custom-select d-block w-100"
                                id="course"
                                required
                            >
                                <option value="">Choose...</option>
                                <option>Cool Golf Course</option>
                                <option>Pittsburgh Golf Land</option>
                                <option>Pound Town Par 3</option>
                            </select>
                        </div>
                    </div>
                    <button class="btn btn-primary btn block" type="submit">
                        Create
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style>
.container {
    background-color: white;
    margin-top: 20px;
    padding: 20px 20px;
    width: 500px;
    border-radius: 5px;
}
</style>
