package com.hystrix.controller;

import com.hystrix.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PARANOIA_ZK
 * @date 2017/12/13 18:45
 */
@RestController
public class DcController {

    //@Autowired
    //LoadBalancerClient loadBalancerClient;

    //@Autowired
    //RestTemplate restTemplate;

    /**
     * 我们注入了LoadBalancerClient和RestTemplate，并在/consumer接口的实现中，
     * 先通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例，
     * 这个服务实例的基本信息存储在ServiceInstance中，然后通过这些对象中的信息拼接出访问/dc接口的详细地址，
     * 最后再利用RestTemplate对象实现对服务提供者接口的调用。
     *
     * eureka-server、eureka-client、eureka-consumer都启动起来，
     * 然后访问http://localhost:2101/consumer ，
     * 来跟踪观察eureka-consumer服务是如何消费eureka-client服务的/dc接口的。
     * @return
     */
    //@GetMapping("/consumer")
    //public String dc(){
    //    ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
    //    String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
    //    System.out.println(url);
    //    return restTemplate.getForObject(url, String.class);
    //}


    /**
     * 可以看到这里，我们除了去掉了原来与LoadBalancerClient相关的逻辑之外，
     * 对于RestTemplate的使用，我们的第一个url参数有一些特别。
     * 这里请求的host位置并没有使用一个具体的IP地址和端口的形式，而是采用了服务名的方式组成。
     * 那么这样的请求为什么可以调用成功呢？因为Spring Cloud Ribbon有一个拦截器，它能够在这里进行实际调用的时候，自动的去选取服务实例，
     * 并将实际要请求的IP地址和端口替换这里的服务名，从而完成服务接口的调用。
     * 在完成了上面你的代码编写之后，可以将eureka-server、eureka-client、eureka-consumer-ribbon都启动起来，
     * 然后访问http://localhost:2101/consumer ，来跟踪观察eureka-consumer-ribbon服务是如何消费eureka-client服务的/dc接口的，
     * 并且也可以通过启动多个eureka-client服务来观察其负载均衡的效果。
     * @return
     */
    /*@GetMapping("/consumer")
    public String dc() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }*/

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc() {
        return consumerService.consumer();
    }
}
