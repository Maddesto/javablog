package maddesto.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import maddesto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(userRepository == null) return true;
		return userRepository.findByName(value) == null;
	}
	
}
