def call(Map config) {
    stage("Setting Up Gradle Environment") {
        node {
           //checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '7daee168-9f44-47b1-bdb6-a90058c89007', url: 'git@github.com:roachmaster/mars-rover-photo-collector.git']]]) 
           checkout scm
           sh "ls" 
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
