# Ribbon LoadBalancer의 Round-Robin은 다운된 서버는 선택하지 않음

https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RoundRobinRule.java

...
public Server choose(ILoadBalancer lb, Object key) {
  ...
  while (server == null && count++ < 10 {
    List<Server> reachableServers = lb.getReachableServer();
    List<Server> allServers = lb.getAllServers();
    int upCount = reachableServers.size();
    int serverCount = allServers.size();
  
    if ((upCount == 0) || (serverCount == 0)) {
      log.warn("No up servers available from load balancer: " + lb);
      return null;
    }
    ...
  }
  ...
}
