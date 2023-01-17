import { defineConfig } from 'cypress'

// baseUrl: 'http://host.docker.internal:4200',

export default defineConfig({
  projectId: 'bi11kh',
  
  e2e: {
    baseUrl: 'http://localhost:4200',
    supportFile: false
  },
  
  
})