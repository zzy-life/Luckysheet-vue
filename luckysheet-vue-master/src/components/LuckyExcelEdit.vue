<!--
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-24 18:33:54
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 22:51:39
-->
<template>
  <div class="hello">
    <div id="lucky" style="margin:0px;padding:0px;position:absolute;width:100%;height:100%;left: 0px;top: 0px;">
    </div>
  </div>
</template>

<script>
  import LuckyExcel from 'luckyexcel';
  export default {
    name: 'LuckyExcelEdit',
    data() {
      return {
        url: '',
      }
    },
    mounted() {
      this.CreateExcel();

    },

    methods: {
      download() {
        return new Promise((resolve, reject) => {
          this.$axios.get(this.url, {
            responseType: 'blob',
          }).then(res => {
            console.log(res);
            resolve(res)
          });
        })
      },
      CreateExcel() {

        this.download().then(res => {
          let filename = res.headers['content-disposition'];

          let filenameindex = filename.indexOf("filename*=utf-8''");

          let result = filename.substr(filenameindex + 17, filename.length);
          filename = decodeURI(result)

          let file = new File([res.data], filename);//创建一个file文件
          // 先确保获取到了xlsx文件file，再使用全局方法window.LuckyExcel转化
          LuckyExcel.transformExcelToLucky(
            file,
            function (exportJson, luckysheetfile) {
              // 配置项
              const options = {
                container: 'lucky', // 设定DOM容器的id
                lang: 'zh',// 设定表格语言
                myFolderUrl:'./',
                data: exportJson.sheets,
                title: exportJson.info.name,
                userInfo: exportJson.info.name.creator,
                editMode: false, allowEdit: false
              }
              // 获得转化后的表格数据后，使用luckysheet初始化，或者更新已有的luckysheet工作簿
              // 注：luckysheet需要引入依赖包和初始化表格容器才可以使用
              luckysheet.create(options);

            },
            function (err) {
              logger.error('Import failed. Is your fail a valid xlsx?');
            }
          );
        })
      },
    },
    created() {
      var url = window.location.href;             //获取当前url

      var dz_url = url.split('#')[0];                //获取#/之前的字符串

      var cs = dz_url.split('?')[1];                //获取?之后的参数字符串

      var cs_arr = cs.split('&');                    //参数字符串分割为数组

      var cs = {};

      for (var i = 0; i < cs_arr.length; i++) {         //遍历数组，拿到json对象

        cs[cs_arr[i].split('=')[0]] = cs_arr[i].split('=')[1]

      }

      this.url = decodeURIComponent(cs.id);
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  /* h3 {
    margin: 40px 0 0;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  } */
  #luckysheet-icon-morebtn{
    padding: 2px 0px 0 5px;
  }
</style>