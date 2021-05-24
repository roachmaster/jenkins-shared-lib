def call(Map config) {
    stage("Setting Up Gradle Environment") {
        node {
           checkout scm
           sh "ls" 
           sh "chmod +x ./scripts/setUpGradleProjects.sh"
           sh "./scripts/setUpGradleProjects.sh"
        }
    }/*
    stage("build") {
        node {
            sh "./gradlew clean build -x test ${jenkinsWS} ${jenkinsBuild}"
        }
    }
    stage("test") {
        node {
            sh "./gradlew test --info ${jenkinsWS} ${jenkinsBuild}"
        }
    }
    stage("Upload Archives") {
        node {
            sh "./gradlew uploadArchives --info ${jenkinsWS} ${jenkinsBuild}"
        }
    }*/
}
