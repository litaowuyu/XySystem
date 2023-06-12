<template>
	<div id="exchangeRecord">
		<RecordHeader></RecordHeader>
		<div style="display: inline-block;">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	import RecordHeader from './RecordHeader.vue'
	export default{
		name:'ExchangeRecord',
		components:{RecordHeader},
		mounted() {
			this.$router.push('/index/userDetail/recordScreener')
		}
	}
</script>

<style scoped>
	#exchangeRecord{
		width:840px;
		height: 500px;
		display: inline-block;
		background-color:white;
		margin-top: 30px;
		padding: 10px;
	}
</style>