
export default {
    name: 'name: "Ds"',
    data() {
        return {

        }
    },
    created() {
        this.getDsData()
    },
    methods: {
        getDsData() {
            this.$axios.get("/zyj/ds/list?searchName="+'aa'+'&code='+ 'aa').then(res => {
                console.log('res:--->', res)
            })
        }
    }
}