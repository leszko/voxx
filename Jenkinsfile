registry = '192.168.1.2'
staging = '192.168.1.3'
production = '192.168.1.4'

image = "$registry:5000/voxx:${env.BUILD_NUMBER}"
container = 'voxx'

node {
   stage 'Checkout'
   git url: 'https://github.com/leszko/voxx.git'

   stage 'Build'
   sh './gradlew clean assemble'
   // sh './mvnw clean compile'

   stage 'Unit Test'
   sh './gradlew check'
   // sh './mvnw test'

   stage 'Docker Build'
   sh "docker build -t $image ."

   stage 'Docker Push'
   sh "docker push $image"

   stage 'Deploy to Staging'
   sh "docker -H $staging:2375 stop $container"
   sh "docker -H $staging:2375 rm $container"
   sh "docker -H $staging:2375 run -p 8080:8080 -d --name $container $image"

   sleep 60

   stage 'Acceptance Test'
   sh "test \'1.00\' = `curl $staging:8080/usd/exchange-rate`"

   stage "QA Manual Approval"
   input "QA Manual Approval"

   stage 'Release'
   sh "docker -H $production:2375 stop $container"
   sh "docker -H $production:2375 rm $container"
   sh "docker -H $production:2375 run -p 8080:8080 -d --name $container $image"

   sleep 60

   stage 'Smoke Test'
   sh "test \'1.00\' = `curl $production:8080/usd/exchange-rate`"
}