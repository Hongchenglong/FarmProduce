import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import WindiCSS from 'vite-plugin-windicss'
import Pages from 'vite-plugin-pages'
import Layouts from 'vite-plugin-vue-layouts';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [WindiCSS(), vue(),Layouts({
    layoutsDirs:'src/layouts',
    defaultLayout:'default'
  }),
  Pages({
    exclude: ['**/components/**/*.{vue,ts,js}', '**/composables/**/*.{vue,ts,js}'],
    nuxtStyle: true
  })],
  resolve:{
    alias:{
      '@':'/src'
    }
  },
  server:{
    port: '8000',
    proxy:{
      '/api':{
        target: 'http://localhost:8001',
        changeOrigin: true,
        // rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
