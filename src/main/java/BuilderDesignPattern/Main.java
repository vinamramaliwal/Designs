package BuilderDesignPattern;

public class Main {
    // Problem ?
    /*
    When there are many optional data in a class which are not mandatory.
    There can be many constructor which take some data as parameters.
    Builder is ideal when your class has:
Many fields (especially optional)
Immutability is desired
Construction logic is non-trivial

     */

    public static void main(String args[]){
        User user = new User.Builder("Vinamra", "vinamra@email.com")
                .phone("9876543210")
                .address("Delhi")
                .age(29)
                .build();

        System.out.println("User: " + user.getName() + ", " + user.getEmail());

    }
}
