
 ## Submission:
- Provide the link to your GitHub repository in the Greenhouse submission form.
- Submit your completed project via the Greenhouse link in the email received from the Recruitment Manager.

## Challenge Application

A Spring Boot application that provides a simple user management REST API.

### Technologies Used
- Java 17
- Spring Boot
- Maven
- MySQL 8
- Docker
- Docker Compose
- Kubernetes
- Minikube
- Helm
- Terraform
- GitHub Actions
- Spring Boot Actuator
- Micrometer Prometheus
- Prometheus
- Grafana

### API Endpoints

#### GET /user?id=1

Retrieves a user by ID
- Response
  Greetings from Crewmeister,Alice!

#### POST /user

Creates a new user

### Running with Docker Compose

#### Prerequisites

#### Install
- Docker Desktop
- Java 
- Maven

Build and start the application:
docker compose up --build

The application will be available at:

http://localhost:8081

Health endpoint:
http://localhost:8081/actuator/health

Prometheus metrics:
http://localhost:8081/actuator/prometheus

Stop the application
docker compose down

### Running on Kubernetes with Minikube
#### Prerequisites

Install
- Docker Desktop
- Minikube
- Kubectl
- Helm

##### Start Minikube:
minikube start --driver=docker

##### Build the Docker image:
docker build -t demo-app:latest .

##### Load the image into Minikube:
minikube image load demo-app:latest

##### The Helm chart usues:
imagePullPolicy: Never
so Kubernetes uses the locally loaded image.

##### Install the Helm release 

helm install demo-app ./helm

For an existing installation,update it with:

helm upgrade demo-app ./helm

Check the deployment:
Kubectl get pods

Expected application state:
demo-app-helm....

MySQL is deployed as part of the Helm chart:
mysql...

Access the application
Port-forward the Kubernetes service.
Kubectl port-forward service/demo-app-helm 8082:8080

Then test the health endpoint:
curl.exe http://localhost:8082/actuator/health

Expected response:
{
"status":"UP"
}

Test the Prometheus endpoint:
curl.exe http://localhost:8082/actuator/prometheus

### Terraform
Terraform is used to interact with the Kubernetes cluster.
The Terraform configuration creates a dedicated Kubernetes namespace called "challenge".

Initialize Terraform:
- cd terraform  
- terraform init

Review the changes:
- terraform apply

Verify the namespace
- kubectl get namespaces

The Terraform state file and provider cache are excluded from GIT.

### Monitoring

The application exposes Spring Boot Actuator endpoints

Enabled endpoints include:
- /actuator/health
- /actuator/info
- /actuator/prometheus

Micrometer exposes application metrics in Prometheus format.

### Prometheus

The Kubernetes environment uses the Prometheus Community kube-prometheus-stack.

The application has a Kubernetes ServiceoMonitor configured to scrape:
/actuator/prometheus

The ServiceMonitor is associated with the Prometheus installation.

Verify it:
kubectl get servicemonitor

### Grafana

Grafana is deployed as a part of the monitoring stack.
Access Grafana locally with:
- kubectl port-forward svc/monitoring-grafana 3000:80

Then open:
http://localhost:3000

Prometheus can be accessed locally with:
- kubectl port-forward svc/monitoring-kube-prometheus-prometheus 9090:9090

### CI Pipeline

GitHub Actions is configured in:
.github/workflows/ci.yml

The pipeline runs on pushes to:
main
feature-devopschallenge

The CI pipeline:
1. Checks out the repository
2. Sets up JAVA 
3. Uses Maven dependency caching.
4. Builds the Spring Boot application.
5. Builds the Docker image.

The Maven build uses:
- mvn clean package -Dskiptests

The Docker image is built with:
- kubectl get pods

The expected application state is
The Spring Boot health endpoint provides an addtional application-level health check:
/actuator/health

### Useful Commands
Check all Kubernetes resources:

- kubectl get all

Check application pods:

- kubectl get pods -l app.kubernetes.io/instance=demo-app

Check services:

- kubectl get services

Check Helm releases:

- helm list

Check ServiceMonitors:

- kubectl get servicemonitor

Check application logs:

- kubectl logs deployment/demo-app-helm

Check MySQL logs:

- kubectl logs deployment/mysql

#### Cleanup

Remove the Helm release:

- helm uninstall demo-app

Remove the Terraform-managed namespace:

- cd terraform
- terraform destroy

Stop Minikube:

- minikube stop

#### Verification

The implementation has been verified locally with:

1. Docker image build
2. Docker Compose configuration
3. Helm lint
4. Helm deployment
5. Kubernetes rollout
6. Spring Boot health endpoint
7. Prometheus metrics endpoint
8. Prometheus scraping
9. Grafana metrics
10. Terraform initialization, plan, and apply
11. GitHub Actions CI

The application, MySQL, Prometheus, and Grafana were successfully running in the local Kubernetes environment.














