<template>
    <div class="app">
        <img alt="Vue logo" src="../assets/logo.png">
        <LuckyExcelEdit msg="Welcome to Your Vue.js App" />
    </div>
</template>
<script>
    import LuckyExcelEdit from '../components/LuckyExcelEdit.vue'
    export default {
        name: 'Index',
        components: {
            LuckyExcelEdit
        }
    }
</script>
<style>
.app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>