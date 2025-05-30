import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/

export default defineConfig({
  server: {
    proxy: {
      // 代理规则
      '/javaapi': {
        target: 'http://0.0.0.0:8080/', // 目标服务器的地址
        changeOrigin: true, // 是否改变请求源
        rewrite: (path) => path.replace(/^\/javaapi/, ''), // 重写路径，去掉 '/api' 前缀
      }

    },
  },
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
