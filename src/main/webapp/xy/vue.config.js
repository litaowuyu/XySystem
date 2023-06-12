const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
	transpileDependencies: true,
	// 关闭语法检查
	lintOnSave:false,
	// 配置代理服务器，解决跨域问题
	devServer:{proxy:'http://localhost:8080'
	}
})
