<template class="rsd">
  <el-form class="elf" label-position="left" label-width="150px" style="max-width: 460px">
    <el-space fill>
      <el-alert type="info" show-icon :closable="false">
        <p>请输入您要申请的店铺地址</p>
      </el-alert>
      <el-form-item label="Shop Information">
        <i class="fa fa-id-card"></i>
        <el-input v-model="ShopForm.name" label="Shop Name" placeholder="Shop Name" />
        <el-divider border-style="dashed" />
        <el-input v-model="ShopForm.storeLocation" label="Shop Location" placeholder="Shop Location" />
      </el-form-item>
    </el-space>
    <el-divider border-style="dotted" />
    <el-space fill>
      <el-alert type="info" show-icon :closable="false">
        <p>
          请输入店主的姓名和手机号
        </p>
      </el-alert>
      <el-form-item label="Your Information">
      <i class="fa fa-user-o"></i>
      <el-input
      v-model="ShopForm.id"
      label="id"
      placeholder="id"
      />
      <el-divider border-style="dashed" />
            <el-input
              v-model="ShopForm.contactPerson"
              label="Real Name"
              placeholder="Real Name"
            />
            <el-divider border-style="dashed" />
            <el-input
              v-model="ShopForm.username"
              label="User Name"
              placeholder="User Name"
            />
            <el-divider border-style="dashed" />
            <el-input
              v-model="ShopForm.contactPhone"
              label="Contact"
              placeholder="Contact"
            />
      </el-form-item>
    </el-space>
    <el-divider border-style="dotted" />
    <template>
      <el-popconfirm confirm-button-text="Yes" cancel-button-text="No" title="Are you sure to submit this?"
        @confirm="confirmEvent(ShopForm)" @cancel="cancelEvent()">
        <template #reference>
          <el-button>Submit</el-button>
        </template>
      </el-popconfirm>
    </template>
  </el-form>
</template>

<script>
import { SubmitMerchantApp, SubmitMerchantAppForm,UserID } from '@/api/api'



export default {
  data() {
    return {
      ShopForm:{
        id:'M010',
        username: 'user30',
        name: 'SuperShop',
        contactPerson: 'Lee',
        contactPhone: '11112222333',
        storeLocation: 'Beihang University'
      },
      AppForm:{
      id: 'MA010',
      merchantId: 'M010',
      storeAddress: '',
      status: 0,
      },
      order:7
      
    }
  },
  methods: {
      confirmEvent(params){
      this.AppForm.storeAddress=this.ShopForm.storeLocation;
      UserID(this.ShopForm.username).then(res=>{
        console.log(res);
        //this.AppForm.merchantId=res.data.userData.id;
        this.AppForm.merchantId=this.ShopForm.id
        this.AppForm.id='MA'+this.AppForm.merchantId.slice(1);
      })
      SubmitMerchantAppForm(this.AppForm).then(res=>{
        console.log(res);
      })
      SubmitMerchantApp(params).then(res => {
        console.log(res);
      })
      //this.$router.replace('/login');
    },
    cancelEvent() {
      console.log("cancel!");
    }
  },
}


</script>
<style lang="scss" scoped>
.rsd {
  width: 100%;
  height: 100%;
  position: relative;
  font-style: oblique;

  .elf {
    width: 100%;
    height: 100%;
    position: relative;
    font-style: oblique;
    background-color: aquamarine;
  }
}
</style>

