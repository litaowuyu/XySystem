<template>
	<div id="userDetail">
		<UserInformation></UserInformation>
		<user-wallet></user-wallet>
		<RecordHeader></RecordHeader>
	</div>
</template>

<script>
	import RecordHeader from './RecordHeader.vue'
	import UserInformation from './UserInformation.vue'
	import UserWallet from './UserWallet.vue'
	export default{
		name:'UserDetail',
		components:{RecordHeader,UserInformation,UserWallet}
	}
</script>

<style scoped>
	#userDetail{
		padding-top: 30px;
		padding-left: 300px;
		padding-right: 300px;
	}
</style>