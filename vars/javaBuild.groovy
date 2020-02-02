def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    stage("Checkout SCM") {
        node {
            checkout scm
        }
    }
    stage("build") {
        node {
            sh "./gradlew clean build -x test"
        }
    }
    stage("test") {
        node {
            sh "./gradlew test --info"
        }
    }
    stage("upload Archives") {
        node {
            withCredentials([usernamePassword(credentialsId: 'roachmaster-maven-repo', usernameVariable: 'mavenUser', passwordVariable: 'mavenPass')]) {
                sh "env ; ./gradlew uploadArchives -PmavenUser=${mavenUser} -PmavenPassword=${mavenPass}"
            }
        }
    }
}