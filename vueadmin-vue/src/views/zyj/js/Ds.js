
export default {
    name: 'name: "Ds"',
    data() {
        return {
            searchName: '',
            dsInfo: {},
            purchaseRecordsData: []
        }
    },
    methods: {
        getInfo() {
            this.getDsData()
            this.getPurchaseRecordsList()
        },
        getDsData() {
            this.$axios.get("zyj/ds/list?searchName="+this.searchName+'&code='+ 'aa').then(res => {
                if(res.data.code === 200 && res.status === 200) {
                    this.dsInfo = res.data.data.data
                    console.log('dsInfo:--->', this.dsInfo)
                }
            })
        },
        getPurchaseRecordsList() {
            this.$axios.get("zyj/ds/purchaseRecordsList?searchName="+this.searchName+'&code='+ 'aa').then(res => {
                if(res.data.code === 200 && res.status === 200) {
                    console.log('res:--->', res)
                    let purchaseRecords = res.data.data.data.purchaseRecords
                    let array = new Array()
                    array = purchaseRecords
                    let arrData = array.filter((v, i) => i < 4);
                    this.purchaseRecordsFor(arrData)
                }
            })
        },
        datedifference(sDate1, sDate2){
            var dateSpan,
                tempDate,
                iDays;
            sDate1 = Date.parse(sDate1);
            sDate2 = Date.parse(sDate2);
            dateSpan = sDate2 - sDate1;
            dateSpan = Math.abs(dateSpan);
            iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
            return iDays + 1
        },
        purchaseRecordsFor(array) {
            let newData = [];
            if (array.length !== 0) {
                newData = array.map(item => {
                    let allDate = this.datedifference(item.startDate, item.endDate);
                    let dayCount = (item.count / allDate).toFixed(1);
                    return {
                        startDate: item.startDate,
                        endDate: item.endDate,
                        allDate,
                        dayCount,
                        count: item.count,
                    }
                });
                this.purchaseRecordsData = newData
            };
        }
    }
}