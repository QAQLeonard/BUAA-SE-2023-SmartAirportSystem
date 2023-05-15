
<template class="rsd">
    <el-form class="elf" label-position="left" label-width="150px" style="max-width: 460px">
      <el-space fill>
        <el-alert type="info" show-icon :closable="false">
        <p>请输入您要申请的店铺地址</p>
        </el-alert>
        <el-form-item label="Shop Location">
            <i class="fa fa-id-card"></i>
            <el-input v-model="ShopForm.ShopName" />
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
                v-model="ShopForm.PersonName"
                label="Your Name"
                placeholder="Your Name"
              />
              <el-divider border-style="dashed" />
              <el-input
                v-model="ShopForm.PhoneNumber"
                label="Contact"
                placeholder="Contact"
              />
        </el-form-item>
      </el-space>
      <el-divider border-style="dotted" />
      <template>
        <el-popconfirm
          confirm-button-text="Yes"
          cancel-button-text="No"
          title="Are you sure to submit this?"
          @confirm="confirmEvent"
          @cancel="cancelEvent"
        >
          <template #reference>
            <el-button>Submit</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import { reactive } from 'vue'
  //import { InfoFilled } from '@element-plus/icons-vue'

  const confirmEvent = () => {
    console.log('confirm!')
  }
  const cancelEvent = () => {
    console.log('cancel!')
  } 
  const ShopForm = reactive({
    ShopName: '',
    PersonName: '',
    PhoneNumber: '',
  })
  </script>
  <style lang="scss" scoped>
  .rsd{
    width: 100%;
    height: 100%;
    position: relative;
    font-style: oblique;
    .elf{
      width: 100%;
      height: 100%;
      position: relative;
      font-style: oblique;
      background-color: aquamarine;
    }
  }
  </style>

  