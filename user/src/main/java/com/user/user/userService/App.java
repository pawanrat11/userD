package com.user.user.userService;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.user.user.models.MY_ADDRESS;
import com.user.user.models.USER;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class App 
{
	public static ArrayList<USER> dummyData = new ArrayList<>(); 
	public static ArrayList<MY_ADDRESS> dummyDataAddress = new ArrayList<>(); 
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
