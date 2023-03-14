package com.jonas.springboot.aws.sns.example;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootAwsSnsExampleApplication {

	@Autowired
	private AmazonSNSClient snsClient;

	String TOPIC_ARN = "arn:aws:sns:sa-east-1:090091789954:jonas-topic";

	@PostMapping("/addSubscription/{email}")
	public String addSubscription(@PathVariable String email){
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
		snsClient.subscribe(request);
		return "Subscription request is pending. To confirm the subscription, check your email :: " + email;
	};

	@PostMapping("/sendNotification")
	public String publishMessageToTopic(){
		PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, buildEmailBody(), "Notification: Network connect issue");
		snsClient.publish(publishRequest);
		return "Notification send successfully !!";
	};

	private String buildEmailBody(){
		return "Dear develops," +
				"" +
				"Connection down in Sao Paulo";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSnsExampleApplication.class, args);
	}

}
