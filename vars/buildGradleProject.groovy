def call(Map config) {
    stage("Building ${config[name]} Gradle Projects") {
        node {
           sh "ls ${config[name]}" 
        }
    }
}
