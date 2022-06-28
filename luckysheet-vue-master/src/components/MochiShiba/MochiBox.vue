<!--
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-27 15:16:12
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 16:57:00
-->
<template lang="">

    <div class="MochiBox /" :class="[shibaTemp, { pop: popTemp }]" @click="randomize" tabindex="0">
        <MochiShiba :size="sizeTemp" :mood="smoodTemp" :leftEye="sleftEyeTemp" :rightEye="rightEyeTemp"
            :leftEar="leftEarTemp" :rightEar="rightEarTemp" :blush="blushTemp" />
        <div class="MochiContent">
            <slot></slot>
        </div>
        <MochiPaws :size="sizeTemp" />
    </div>

</template>
<script>
    import MochiShiba from '@/components/MochiShiba/MochiShiba.vue';
    import MochiPaws from '@/components/MochiShiba/MochiPaws.vue';
    // Array.prototype.random = function () {
    //     return this[Math.floor(Math.random() * this.length)];
    // };
    export default {
        name: "Mochibox",
        components: {
            MochiShiba,
            MochiPaws
        },
        props: {
            shiba: { type: String, default: "okaka" },
            size: { type: String, default: "medium" },
            mood: { type: String, default: "" },
            leftEye: { type: String, default: "open" },
            rightEye: { type: String, default: "open" },
            leftEar: { type: String, default: "up" },
            rightEar: { type: String, default: "flat" },
            blush: { type: Boolean, default: false },
            pop: { type: Boolean, default: true }
        },
        data() {
            return {
                // 避免直接修改props
                shibaTemp: this.shiba,
                sizeTemp: this.size,
                moodTemp: this.mood,
                leftEyeTemp: this.leftEye,
                rightEyeTemp: this.rightEye,
                leftEarTemp: this.leftEar,
                rightEarTemp: this.rightEar,
                blushTemp: this.blush,
                popTemp: this.pop,
            }
        },

        mounted() {
            let _ = this;
            let time = 3000 + Math.random() * 2000;
            setTimeout(() => {
                _.popTemp = false;
            }, time);
        },
        methods: {
            randomMath(arr) {
                return arr[Math.floor(Math.random() * arr.length)];
            },
            randomize() {
                if (this.canRandom) {
                    this.shibaTemp = this.randomMath(["ume", "sesame", "tuna", "okaka", "anko", "kinako", "sakura", "monaka"]);
                    this.smoodTemp = this.randomMath(["", "happy", "content", "excited", "cheeky", "drool", "cute", "gleam"]);
                    this.sleftEyeTemp = this.randomMath(["open", "wink", "shy", "laugh"]);
                    this.rightEyeTemp = this.randomMath(["open", "wink", "shy", "laugh"]);
                    this.leftEarTemp = this.randomMath(["up", "down", "flat", "middle"]);
                    this.rightEarTemp = this.randomMath(["up", "down", "flat", "middle"]);
                    this.blushTemp = this.randomMath([true, false]);
                }
            }
        },
        created() {
            if (this.shibaTemp === "random") {
                this.canRandom = true;
                this.randomize();
            }
        }
    }
</script>
<style lang="">

</style>