<template>
  <div>
    <div class="search">
      <input class="search_input" v-model="searchName"></input>
      <button @click="getInfo">搜索</button>
    </div>
    <h1 style="margin-left: 124px;">基本信息</h1>
    <div class="basic-info">
      <table class="check-black-info-table checkinfo">
        <thead>
        <tr>
          <td class="check-black-info-td">买家：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="wangwang">{{dsInfo.wangwang}}</span></td>
          <td class="check-black-info-td">买家信誉：</td>
          <td style="width: 200px">
            <div class="buyerCredit">
									<span class="buyerCreditSpan">
										<img style="height: 16px;" id="buyerGoodNumicon" src="https://upload.chadianshang.com/s_crown_1.gif" title="1黄冠">
										<span style="display: none;" class="NobuyerCreditNum">0心</span>
									</span>
            </div>
          </td>
          <td class="check-black-info-td">商家信誉：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="cedit">1心</span></td>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td class="check-black-info-td">性别：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="gender">{{dsInfo.gender}}</span></td>
          <td class="check-black-info-td">收到好评率：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="receivedRate">{{dsInfo.sentRate}}</span></td>
          <td class="check-black-info-td">注册日期：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="wwcreatedStr">{{dsInfo.wwcreatedStr}}</span></td>
        </tr>
        <tr>
          <td class="check-black-info-td">淘龄：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="taoling">{{dsInfo.taoling + '+'}}</span></td>
          <td class="check-black-info-td">买家总周平均：</td>
          <td style="width: 200px">&nbsp;&nbsp;
            <span id="weekCreditAverage">{{dsInfo.weekCreditAverage}}</span>
            <img class="cp" src="https://upload.chadianshang.com/yiwen.png" alt="" onmouseover="show_shopm(this,'买家帐号注册起到现在总的周平均评价点数');" onmouseout="close_shopm();">
          </td>
          <td class="check-black-info-td">查询日期：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="searchDate">{{dsInfo.searchTime}}</span></td>
        </tr>
        <tr>
          <td class="check-black-info-td">实名认证：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="renZheng">{{dsInfo.renZheng}}</span></td>
          <td class="check-black-info-td">会员等级：</td>
          <td style="width: 200px" class="red-color">&nbsp;&nbsp;
            <span id="vip">{{dsInfo.vip}}</span>
            <span id="vipLevel" class="" style="display: inline-block;position: relative; top:4px"></span>
            <img class="vipImg" src="https://upload.chadianshang.com/202104191831043358.png">
          </td>
          <td class="check-black-info-td">给出好评率：</td>
          <td style="width: 200px">&nbsp;&nbsp;<span id="sentRate">{{dsInfo.sentRate + '%'}}</span></td>
        </tr>
        <tr>
          <td class="check-black-info-td">给出中差评数：</td>
          <td colspan="1" style="width: 200px">&nbsp;&nbsp;<span id="badTotal">{{dsInfo.badTotal}}</span></td>
          <td class="check-black-info-td" style="color: rgba(234, 51, 35, 1)">淘气值</td>
          <td style="width: 200px">
								<span id="searchtqz">
									&nbsp;&nbsp;
									<span class="red-color" style="cursor: pointer;">
										点击查看
									</span>
								</span>
            <img class="cp" src="https://upload.chadianshang.com/yiwen.png" alt="" onmouseover="show_shopm(this,'截图或者发送淘口令给买家，买家打开后截图淘气值数据! ');" onmouseout="close_shopm();">
          </td>
          <td class="check-black-info-td">旺旺是否88VIP：</td>
          <td style="width: 200px;" id="is88vip">&nbsp;&nbsp;<span class="red-color" onclick="get88vip('aa');" style="cursor: pointer;">点击查看</span></td>
        </tr>
        <tr>
          <td class="check-black-info-td" style="border-bottom: 0px solid red;">周查询</td>
          <td colspan="5" style="border-bottom: 0px solid red;">
            <div class="red-color ft14">
              &nbsp;&nbsp;该用户近一周查询商家数： <font id="weekCount">{{dsInfo.weekCount}}</font>， 上一周查询商家数：<font id="countBefore">{{dsInfo.countBefore}}</font>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <h1 style="margin-left: 124px;">近期购买记录</h1>
    <div class="basic-info">
      <table class="check-black-buy-table recordtable">
        <thead>
        <tr class="gray-bg">
          <th>起点时间</th>
          <th>结束时间</th>
          <th>区间天数</th>
          <th>收货量</th>
          <th>日均收货量</th>
        </tr>
        </thead>
        <tbody class="buyRecordsRight ft14" id="buyrecord">

        <tr :key="index" v-for="(item,index) in purchaseRecordsData">
          <td><span>{{item.startDate}}</span></td>
          <td><span>{{item.endDate}}</span></td>
          <td><span class="red-color fw600">{{item.allDate}}</span></td>
          <td><span class="red-color fw600">{{item.count}}</span></td>
          <td><span class="red-color fw600">{{item.dayCount}}</span></td>
        </tr>
        </tbody>
      </table>
    </div>
    <h1 style="margin-left: 124px;">用户被打标记录 </h1>
    <div class="basic-info">
      <table class="check-black-buy-table recordtable">
        <thead>
        <tr class="gray-bg">
          <th>打标类型</th>
          <th>狐狸</th>
          <th>降权处置</th>
          <th>云黑名单</th>
        </tr>
        </thead>
        <tbody class="buyRecordsRight ft14">
        <tr>
          <td><span>打标次数</span></td>
          <td><span>{{dsInfo.fox}}</span></td>
          <td><span class="red-color fw600">{{dsInfo.jiangNum}}</span></td>
          <td><span class="red-color fw600">{{dsInfo.yunBlack}}</span></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import ds from "./js/Ds.js"
export default ds
</script>

<style scoped>
  .search_input {
    width: 376px;
    height: 40px;
    border: 2px solid red;
    background: #ffffff;border: 2px solid #ea3323;
    border-radius: 100px 0px 0px 100px;
    margin-left: 325px;
    margin-top: 25px;
  }
  .search button {
    width: 100px;
    height: 46px;
    padding-bottom: 5px;
    background: #ea3323;
    border-radius: 0px 100px 100px 0px;
    border: 1px solid #ea3323;
    font-size: 15px;
    font-weight: 400;
    color: #fff;
  }
  .basic-info {
    margin-left: 125px;
    margin-top: 35px;
    border: 1px solid #ddd;
    width: 900px;
    padding: 0px;
  }
  .check-black-info-table td {
    width: 117px;
    height: 35px;
    border-bottom: 1px solid #ddd;
    font-size: 14px;
    text-align: center;
    height: 45px;
  }
  .check-black-info-td {
    background: #f8f8f8;
    color: #999999;
  }
  .check-black-buy-table {
    border: 1px solid #ddd;
    text-align: center;
  }
  .check-black-buy-table td{
    border-right: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    height: 45px;
  }
  .recordtable {
    display: table;
    width: 100%;
    table-layout: fixed;
  }
  .recordtable th {
    border-right: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    height: 45px;
    font-size: 14px;
    width: 150px;
    text-align: center;
    font-weight: 400;
  }
</style>