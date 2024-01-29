# Pipeline

## Minikube
- minikube start
- minikube stop
- minikube status

## Executar os comandos para inicializar os pods
- kubectl apply -f pipeline/application

## Comandos para vizualizar os pods executando
- kubectl get pod --watch

## Comando para limpar o cluster
- kubectl delete --all deployment
- kubectl delete --all pod
- kubectl delete --all secrets
- kubectl delete --all svc



## Execute o comando abaixo para abrir a url que tem conexão com o pod
- minikube service tech-challenge-queue-service --url

- Swagger: http://<Ip>:30001/swagger-ui/index.html