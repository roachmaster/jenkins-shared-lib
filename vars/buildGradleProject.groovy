def call(Map config) {
   sh "cd ${config.name}" 
   sh "./gradlew clean build"
   sh "cd ../" 
   sh "pwd"
}
