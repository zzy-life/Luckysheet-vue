<!--
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-27 17:21:13
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 22:58:41
-->
<template lang="">
    <div>
        <div :id="WorkBookEntity.option.container"
            style="margin:0px;padding:0px;position:absolute;width:100%;height:100%;left: 0px;top: 0px;">
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                WorkBookEntity: {
                    option: {
                        container: 'lucky',
                        lang: 'zh',
                        data: [],
                        title: '',
                        userInfo: '',
                    }
                },
            }
        },
        created() {
            this.getExcel(this.$route.params.id).then(res => {
                this.createExcel()
            })

        },
        methods: {
            getExcel(wbId) {
                return new Promise((resolve, reject) => {
                    this.$axios.get(this.VueApiUrl + '/index/' + wbId, {

                    }).then(res => {
                        console.log(res.data.data);
                        this.WorkBookEntity = res.data.data;
                        resolve(res.data.data)
                    }).catch(err => {
                        alert('服务器未开启');
                    });
                })

            },
            createExcel() {
                var localurl =  this.VueApiUrl; 

                // 配置项
                var options = {
                    container: this.WorkBookEntity.option.container, // 设定DOM容器的id
                    title: this.WorkBookEntity.option.title, // 设定表格名称
                    lang: this.WorkBookEntity.option.lang,
                    allowUpdate: true,
                    enableAddRow:false,
                    myFolderUrl:'./',
                    loadUrl: window.location.protocol + localurl + "/load/" + this.WorkBookEntity.id,
                    loadSheetUrl: window.location.protocol + localurl + "/loadSheet/" + this.WorkBookEntity.id,
                    updateUrl: "ws://" + window.location.host + "/ws/" + Math.round(Math.random() * 100) + "/" + this.WorkBookEntity.id
                    // 更多其他设置...
                }
                // 初始化表格
                luckysheet.create(options)
            }
        },
    }
</script>
