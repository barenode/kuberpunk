# Kubernetes dev

## setting up local registry

https://minikube.sigs.k8s.io/docs/handbook/registry/

---

> minikube addons enable registry
> kubectl get pods --all-namespaces

NAMESPACE     NAME                               READY   STATUS    RESTARTS   AGE
kube-system   coredns-f9fd979d6-p527v            1/1     Running   1          45h
kube-system   etcd-minikube                      1/1     Running   1          45h
kube-system   kube-apiserver-minikube            1/1     Running   1          45h
kube-system   kube-controller-manager-minikube   1/1     Running   1          45h
kube-system   kube-proxy-928nd                   1/1     Running   1          45h
kube-system   kube-scheduler-minikube            1/1     Running   1          45h
kube-system   registry-8d2v7                     1/1     Running   0          12m
kube-system   registry-proxy-rrlj5               1/1     Running   0          12m
kube-system   storage-provisioner                1/1     Running   2          45h

> kubectl port-forward --namespace kube-system registry-8d2v7 5000:5000
> docker run --rm -it --network=host alpine ash -c "apk add socat && socat TCP-LISTEN:5000,reuseaddr,fork TCP:host.docker.internal:5000"
---


## hello 
---
> docker image build -t localhost:5000/hello .
> docker push localhost:5000/hello

> kubectl run demo --image=localhost:5000/hello --port=9999 --labels app=demo
> kubectl get pods

NAME   READY   STATUS    RESTARTS   AGE
demo   1/1     Running   0          9s

> kubectl port-forward demo 9999:8888
> kubectl delete pod demo
---


## hello-k8s
---
> kubectl apply -f k8s/deployment.yaml
> kubectl apply -f k8s/service.yaml
> kubectl port-forward service/demo 9999:9999

> kubectl get services

NAME         TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
demo         ClusterIP   10.98.115.214   <none>        9999/TCP   3m4s
kubernetes   ClusterIP   10.96.0.1       <none>        443/TCP    38d

> kubectl delete -f k8s/

deployment.apps "demo" deleted
service "demo" deleted

---


## querying
---
> kubectl get nodes
> kubectl get services
> kubectl describe pod/demo-6cf66df5d8-7frqb
---


## helm (hello-helm)

---
> kubectl apply -f helm-auth.yaml

> helm install demo ./k8s/demo

NAME: demo
LAST DEPLOYED: Wed Feb 10 12:34:06 2021
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None

> helm list

NAME    NAMESPACE       REVISION        UPDATED                                 STATUS          CHART           APP VERSION
demo    default         1               2021-02-10 12:34:06.340984 +0100 CET    deployed        demo-1.0.1
---


## coomands

---
> kubectl run demo --image=localhost:5000/hello --dry-run -o yaml
> kubectl config get-contexts
> kubectl cluster-info
