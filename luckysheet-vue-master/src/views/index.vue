<!--
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-27 14:50:31
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 22:26:18
-->
<template>
    <div class="index">
        <img alt="Vue logo" src="../assets/logo.png">
        <div class="htmleaf-container">
            <header class="htmleaf-header">
                <h1>基于Luckysheet的在线表格 </h1>
                <a href="https://github.com/mengshukeji/Luckysheet" title="Luckysheet" target="_blank"><span>友情链接：
                        Luckysheet</span></a>
            </header>
            <h1>后端语言：JAVA</h1>
            <p class="sub">技术栈：Springboot + MongoDB + vue2</p>
            <Mochibox shiba="random">
                <h3>
                    新建表格
                </h3>
                <a href="javascript:" @click="createExcel()"> <img src="../assets/img/create.png" /></a>
            </Mochibox>

            <Mochibox shiba="random" v-for="item in ExcelEntityList">
                <h3>
                    <a target="_blank" :href="'/sheel/excel/'+item.id">{{item.option.title}}</a>

                </h3>
                <a target="_blank" :href="'/sheel/excel/'+item.id"> <img src="@/assets/img/excel.png" /></a>
            </Mochibox>

        </div>
    </div>

</template>

<script>
    import Mochibox from '@/components/MochiShiba/MochiBox.vue';
    export default {
        name: 'Index',
        components: {
            Mochibox
        },
        data() {
            return {
                ExcelEntityList: [],
            }
        },
        created() {

            this.getExcelEntityList();
        },
        methods: {
            getExcelEntityList() {
                this.$axios.get(this.VueApiUrl + '/index', {

                }).then(res => {
                    this.ExcelEntityList = res.data.data

                }).catch(err => {
                    alert('服务器未开启');
                });;
            },
            createExcel() {
                this.$axios.get(this.VueApiUrl + '/index/create', {

                }).then(res => {
                    this.$router.push({ path: '/excel/' + res.data.data })
                });
            }
        },
    }
</script>
<style>
    @import '../assets/css/normalize.css';
    @import '../assets/css/htmleaf-demo.css';
    @import '../assets/css/style.css';

    .index {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>