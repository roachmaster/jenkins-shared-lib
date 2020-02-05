def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def jenkinsWS = " -PjenkinsWorkspace=${env.WORKSPACE} "
    def jenkinsBuild = " -PjenkinsBuild=${env.BUILD_NUMBER} "
    stage("Checkout SCM") {
        node {
            sh "echo ${jenkinsWS}"
            sh "echo ${jenkinsBuild}"
            checkout scm
        }
    }
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
            //sh "env ;export GRADLE_USER_HOME='/var/lib/jenkins/.gradle'; ./gradlew uploadArchives --info ${jenkinsWS} ${jenkinsBuild}"
        }
    }
}