def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    stage("Checkout SCM") {
        node {
            sh "env"
            checkout scm
        }
    }
    def jenkinsData = " -PjenkinsWorkspace=${WORKSPACE} -PjenkinsBuild=${BUILD_NUMBER}"
    stage("build") {
        node {
            sh "./gradlew clean build -x test ${jenkinsData}"
        }
    }
    stage("test") {
        node {
            sh "./gradlew test --info ${jenkinsData}"
        }
    }
    stage("Upload Archives") {
        node {
            sh "env ;export GRADLE_USER_HOME='/var/lib/jenkins/.gradle'; ./gradlew uploadArchives --info ${jenkinsData}"
        }
    }
}