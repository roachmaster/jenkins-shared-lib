def call(Map config) {
    checkout scm
    sh "ls" 
    sh "chmod +x ./scripts/*"
    sh script:"./scripts/setUpGradleProjects.sh", returnStatus:true
    sh "which gradle"
}
