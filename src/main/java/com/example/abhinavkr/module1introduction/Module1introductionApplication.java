package com.example.abhinavkr.module1introduction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	private final CakeBaker cakeBaker;

	public Module1introductionApplication(CakeBaker cakeBaker) {
		this.cakeBaker = cakeBaker;
	}

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) {
		cakeBaker.bakeCake();
	}
}
