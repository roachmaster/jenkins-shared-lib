def call(Map config) {
    stage("Setting Up Gradle Environment") {
        node {
           checkout scm
           sh "ls" 
           sh "chmod +x ./scripts/*"
           sh "./scripts/setUpGradleProjects.sh"
           println "done"
        }
    }
}
