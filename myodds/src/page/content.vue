<template>
  <div :model="oddsQuery" ref="oddsQuery">
    <div >
      <el-date-picker v-model="oddsQuery.time" type="date" value-format="yyyyMMdd"  @change="getTime" placeholder="选择日期" style="width:150px"></el-date-picker>
      <button @click="query()">查询</button><button @click="getOdds()">同步</button>
      <el-table class="table-data" :data ="oddsQuery.oddslist" stripe border>
        <el-table-column label="序号" prop="fjcid" header-align="center">
        </el-table-column>
        <el-table-column label="联赛"  prop="flsmc" header-align="center"  align="center">
          </el-table-column>
        <el-table-column label="主队"  prop="fzdmc" header-align="center"   align="center">
        </el-table-column>
        <el-table-column label=""  header-align="center" align="center" width="30px">
          <template scope="scope">
            <div><span >VS</span></div>
          </template>
        </el-table-column>
        <el-table-column label="客队" prop="fkdmc"  header-align="center"  align="center">
        </el-table-column>
        <el-table-column label="让球"  header-align="center"  align="center" width="45px">
          <template scope="scope">
            <div><span ></span>&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div><span >{{scope.row.frq}}</span>&nbsp;&nbsp;</div>
          </template>
        </el-table-column>
        <el-table-column label="赔率"  header-align="center" align="center">
          <template scope="scope">
            <div><span >{{scope.row.win}}</span>&nbsp;&nbsp;<span >{{scope.row.draw}}</span>&nbsp;&nbsp;<span >{{scope.row.lose}}</span>&nbsp;&nbsp;</div>
            <div><span >{{scope.row.rwin}}</span>&nbsp;&nbsp;<span >{{scope.row.rdraw}}</span>&nbsp;&nbsp;<span >{{scope.row.rlose}}</span>&nbsp;&nbsp;</div>
          </template>
        </el-table-column>
        <el-table-column label="结果"  header-align="center"  align="center">
          <template scope="scope">
           <div><span >{{scope.row.fres}}</span></div>
            <div><span>{{scope.row.frqres}}</span></div>
          </template>
        </el-table-column>
        <el-table-column label="分析"  header-align="center"  align="center">
          <template scope="scope">
            <el-button size="small" type="primary" icon="edit" @click="handleEdit(scope.row.fjcid)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  export default {

    data() {
      return {
        oddsQuery:{
          time:"",
          typeId:'',
          ls:"",
          win:'',
          draw:'',
          lose:'',
          res:'',
          items:[{name:"请选择",id:''},{name:"星期一",id:'星期一'},{name:"星期二",id:'星期二'},{name:"星期三",id:'星期三'},{name:"星期四",id:'星期四'},{name:"星期五",id:'星期五'},{name:"星期六",id:'星期六'},{name:"星期日",id:'星期日'}],
          oddslist:[],
          lslist:[{lsmc:"请选择",fid:""}],

        },
      };
    },
    created() {
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getUTCMonth()+1;
        var d = date.getDate();
        if(m<=9){
          m="0"+m;
        }
        if(d<=9){
          d="0"+d;
        }
        this.oddsQuery.time=y+""+m+""+d;
    },
    mounted(){
      this.$nextTick(function () {
        this.$api.post('/my/selectBsxx', {
          "dataStr":this.oddsQuery.time,
        }, res => {
          this.oddsQuery.oddslist = res.data;
        })
      })
    },
    methods: {
      query() {
        this.$api.post('/my/selectBsxx', {
          "dataStr":this.oddsQuery.time
        }, res => {
          this.oddsQuery.oddslist = res.data;
        })
      },
      getTime(date){
        this.oddsQuery.time=date;
      },
      getOdds(){
        this.$api.post('/my/jsodds', {
          "dataStr":this.oddsQuery.time
        }, res => {
          this.$message.success('同步成功');
        })
      },
      handleEdit(data){
        var storage=window.localStorage;
        storage.setItem("fjcid",data);
        this.$router.push({name:'oddsList',params: {fjcid:data} })
      }
    }
  };
</script>

<style>
  @import "../style/scss/_content.scss";
</style>
