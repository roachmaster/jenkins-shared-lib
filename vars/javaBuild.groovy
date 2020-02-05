def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def jenkinsData = " -PjenkinsWorkspace=${env.PWD} -PjenkinsBuild=${env.BUILD_NUMBER}"
    stage("Checkout SCM") {
        node {
            sh "echo ${env.WORKSPACE}"
            sh "echo ${env.BUILD_NUMBER}"
            checkout scm
        }
    }
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
            //sh "env ;export GRADLE_USER_HOME='/var/lib/jenkins/.gradle'; ./gradlew uploadArchives --info ${jenkinsData}"
        }
    }
}