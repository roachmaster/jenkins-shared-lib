def call(config,body) {
    // evaluate the body block, and collect configuration into the object
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    stage("Checkout SCM") {
        node {
            env.WORKSPACE = sh(returnStdout: true, script: 'pwd').trim()
            checkout scm
        }
    }
    def jenkinsWS = " -PjenkinsWorkspace=${this.env.WORKSPACE} "
    def jenkinsBuild = " -PjenkinsBuild=${this.env.BUILD_NUMBER} "
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