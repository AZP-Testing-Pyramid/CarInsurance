import { defineConfig } from 'cypress'

export default defineConfig({
  projectId: 'bi11kh',
  
  e2e: {
    'baseUrl': 'http://localhost:4200',
    supportFile: false
  },
  
  
})