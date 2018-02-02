package hello;

import org.springframework.stereotype.Component;

@Component
public class PersonRepoImpl implements PersonRepo {

	@Override
	public PersonForm getByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPerson(String name, int age) {
		// TODO Auto-generated method stub
		
	}

/*	@Override
	public PersonForm getByAge(int age) {
		
//		return new PersonForm("aloha", age);
	}

	@Override
	public void addPerson(String name, int age) {
		
		
	}*/
	
}