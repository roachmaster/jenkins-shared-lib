def call(Map config) {
    dir(config.name){
        sh "chmod +x ./gradlew"
        sh "./gradlew clean build"
        sh "pwd"
    }
}
