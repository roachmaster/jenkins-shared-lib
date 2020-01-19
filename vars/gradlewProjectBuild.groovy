def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    stage("build") {
        node {
            checkout scm
            sh "ls"
            sh "./gradlew clean build --stacktrace --info"
        }
    }
}