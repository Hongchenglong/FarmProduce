import {defineConfig} from 'windicss/helpers'

function range(size, startAt = 1) {
  return Array.from(Array(size).keys()).map((i) => i + startAt)
}

export default defineConfig({
  darkMode: 'class',
  transformCSS: 'pre',
  // :class时能被识别到
  safelist: [range(3).map((i) => `p-${i}`), range(10).map((i) => `mt-${i}`)],
  // 添加属性前缀
  attributify: {
    prefix: 'w:'
  },
  plugins: [
    ({ addUtilities }) => {
      const newUtilities = {
        '.flex-center-center': {
          display: 'flex',
          'justify-content': 'center',
          'align-items': 'center'
        },
        '.flex-between-center': {
          display: 'flex',
          'justify-content': 'space-between',
          'align-items': 'center'
        }
      }

      addUtilities(newUtilities, ['responsive', 'hover'])
    },
  ],
  theme: {
    extend: {
      colors: {
        primary: '#1da9ff',
        success: '#07c160',
        error: '#ff4b31',
        warning: '#ff731c',
        disabled: '#ccc',
        text: '#212121',
        subtext: '#5f5f5f'
      }
    }
  }
})
