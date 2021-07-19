package example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestSome {

    @Autowired
    RestTemplate restTemplate ;

    @GetMapping(value = "/add")
    public  ResponseEntity<?> getSomething(){
//        Thread t = new Thread();
        List<Dto> lis = new ArrayList<>();
        Dto dto = new Dto("a", "b");
        Dto dto1 = new Dto("c", "d");
        lis.add(dto);
        lis.add(dto1);

        return ResponseEntity.status(HttpStatus.OK).body(lis);
    }

    @GetMapping(value = "/sample")
    public ResponseEntity<?> getResult(){
        URI uri = URI.create("http://localhost:8080/api/add");

        ResponseEntity<List<Dto>> dto = (ResponseEntity<List<Dto>>) restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Dto>>() {});

        return ResponseEntity.status(HttpStatus.OK).body(dto.getBody());
    }

}
