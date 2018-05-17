package hello;

public interface PersonRepo {
	PersonForm getByAge(int age);
	
	void addPerson(String name, int age);
}