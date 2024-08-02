<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="Order_No" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.order_no | orderNoFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Price" width="195" align="center">
      <template slot-scope="scope">
        ¥{{ scope.row.price | toThousandFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Status" width="100" align="center">
      <template slot-scope="{row}">
        <el-tag :type="row.status | statusFilter">
          {{ row.status }}
        </el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
// import { transactionList } from '@/api/remote-search'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: [
        { order_no: '123456789012345678901234567890', price: 1234.56, status: 'success' },
        { order_no: '223456789012345678901234567890', price: 2345.67, status: 'pending' },
        { order_no: '323456789012345678901234567890', price: 3456.78, status: 'success' },
        { order_no: '423456789012345678901234567890', price: 4567.89, status: 'pending' },
        { order_no: '523456789012345678901234567890', price: 5678.90, status: 'success' },
        { order_no: '623456789012345678901234567890', price: 6789.01, status: 'pending' },
        { order_no: '723456789012345678901234567890', price: 7890.12, status: 'success' },
        { order_no: '823456789012345678901234567890', price: 8901.23, status: 'pending' }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // fetchData() {
    //   transactionList().then(response => {
    //     this.list = response.data.items.slice(0, 8)
    //   })
    // }
  }
}
</script>
