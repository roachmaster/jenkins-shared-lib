def call(Map pipelineParams) {
    stage("build") {
        node {
            checkout scm
            sh "ls"
            sh "./gradlew clean build --stacktrace --info"
        }
    }
}