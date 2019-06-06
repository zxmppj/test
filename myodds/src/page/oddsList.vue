<template>
  <div>
    <div>
      <el-checkbox label="胜" v-model="checkedWin"  border size="50px"></el-checkbox>
      <el-checkbox label="平"  v-model="checkedDraw"  border size="50px"></el-checkbox>
      <el-checkbox label="负"  v-model="checkedLose"  border size="50px"></el-checkbox>
      <el-checkbox label="时间"  v-model="checkedTime"  border size="50px"></el-checkbox>
      <button @click="query()">查询</button>
    </div>
  <el-row >
    <el-col :span="8">
      <div class="grid-content">
        <div align="center">
        <span>竞彩初陪</span>
        </div>
      <div style="height: 400px">
        <el-table class="table-data" :data ="oddsClist" stripe border width="300px" height="400px">
          <el-table-column label="序号" prop="fjcid" header-align="center">
          </el-table-column>
          <el-table-column label="联赛" prop="flsmc" header-align="center">
          </el-table-column>
          <el-table-column label="让球" header-align="center" width="45px">
            <template scope="scope">
              <div><span ></span>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              <div><span >{{scope.row.frq}}</span>&nbsp;&nbsp;</div>
            </template>
          </el-table-column>
          <el-table-column label="赔率"  header-align="center" align="center">
            <template scope="scope">
              <div><span >{{scope.row.win}}</span>&nbsp;&nbsp;<span >{{scope.row.draw}}</span>&nbsp;&nbsp;<span >{{scope.row.lose}}</span>&nbsp;&nbsp;</div>
              <div><span >{{scope.row.jwin}}</span>&nbsp;&nbsp;<span >{{scope.row.jdraw}}</span>&nbsp;&nbsp;<span >{{scope.row.jlose}}</span>&nbsp;&nbsp;</div>
            </template>
          </el-table-column>
          <el-table-column label="结果"  header-align="center" width="45px">
            <template scope="scope">
              <div><span >{{scope.row.fres}}</span></div>
              <div><span>{{scope.row.frqres}}</span></div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div></el-col>
    <el-col :span="8">
      <div class="grid-content">
        <div align="center">
          <span>竞彩即时盘</span>
        </div>
        <div style="height: 400px">
          <el-table class="table-data" :data ="oddsJlist" stripe border height="400px">
            <el-table-column label="序号" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="联赛" prop="flsmc" header-align="center">
            </el-table-column>
            <el-table-column label="让球" header-align="center" width="45px">
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
            <el-table-column label="结果"  header-align="center" width="45px">
              <template scope="scope">
                <div><span >{{scope.row.fres}}</span></div>
                <div><span>{{scope.row.frqres}}</span></div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="grid-content">
        <div align="center">
          <span>竞彩即时盘让球</span>
        </div>
        <div style="height: 400px">
          <el-table class="table-data" :data ="roddsJlist" stripe border height="400px">
            <el-table-column label="序号" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="联赛" prop="flsmc" header-align="center">
            </el-table-column>
            <el-table-column label="让球" header-align="center" width="45px">
              <template scope="scope">
                <div><span ></span>&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <div><span >{{scope.row.frq}}</span>&nbsp;&nbsp;</div>
              </template>
            </el-table-column>
            <el-table-column label="赔率"  header-align="center" align="center">
              <template scope="scope">
                <div><span >{{scope.row.win}}</span>&nbsp;&nbsp;<span >{{scope.row.draw}}</span>&nbsp;&nbsp;<span >{{scope.row.lose}}</span>&nbsp;&nbsp;</div>
              </template>
            </el-table-column>
            <el-table-column label="结果"  header-align="center" width="45px">
              <template scope="scope">
                <div><span >{{scope.row.fres}}</span></div>
                <div><span>{{scope.row.frqres}}</span></div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-col>
  </el-row>
    <br/>
  <el-row>
    <el-col :span="12">
      <div class="grid-content">
        <div align="center">
          <span>威廉初陪</span>
        </div>
        <div>
          <el-table class="table-data" :data ="oddsCWlist" stripe border width="300px">
            <el-table-column label="序号" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="联赛" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="让球" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="胜" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="结果" prop="fjcid" header-align="center">
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content">
        <div align="center">
          <span>威廉即时</span>
        </div>
        <div>
          <el-table class="table-data" :data ="oddsCWlist" stripe border width="300px">
            <el-table-column label="序号" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="联赛" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="让球" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="胜" prop="fjcid" header-align="center">
            </el-table-column>
            <el-table-column label="结果" prop="fjcid" header-align="center">
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-col>
  </el-row>
  </div>



</template>

<script>
    export default {

        data(){
            return{
              checkedWin:true,
              checkedDraw:true,
              checkedLose:true,
              checkedTime:false,
              oddsClist:[],
              oddsJlist:[],
              roddsJlist:[],
              fjcid:"",
            }
        },
      created(){
          if(typeof a == "undefined" ){
            var storage=window.localStorage;
            this.fjcid=storage.getItem("fjcid");
          }
      },
      methods:{
        query(){
          //竞彩初盘分析
          this.$api.post('/my/oddsCpFx', {
            "fjcid":this.fjcid,
            "checkedWin":this.checkedWin,
            "checkedDraw":this.checkedDraw,
            "checkedLose":this.checkedLose,
            "checkedTime":this.checkedTime,
          }, res => {
            this.oddsClist = res.data;
          });
          //竞彩初盘分析
          this.$api.post('/my/oddsJpFx', {
            "fjcid":this.fjcid,
            "checkedWin":this.checkedWin,
            "checkedDraw":this.checkedDraw,
            "checkedLose":this.checkedLose,
            "checkedTime":this.checkedTime,
          }, res => {
            this.oddsJlist = res.data;
          });
          //竞彩初盘分析
          this.$api.post('/my/roddsJpFx', {
            "fjcid":this.fjcid,
            "checkedWin":this.checkedWin,
            "checkedDraw":this.checkedDraw,
            "checkedLose":this.checkedLose,
            "checkedTime":this.checkedTime,
          }, res => {
            this.roddsJlist = res.data;
          });
        },
      }
    }
</script>

<style scoped>

</style>
