package BuilderDesignPattern;

import lombok.Data;

@Data
public class User {
    // Required fields
    private final String name;
    private final String email;

    // Optional fields
    private final String phone;
    private final String address;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.age = builder.age;
    }

    public static class Builder{

        private final String name;
        private final String email;

        private String phone;
        private String address;
        private int age;

        public Builder(String name, String email){
            this.name = name;
            this.email = email;
        }

        public Builder phone(String phone){
            this.phone = phone;
            return  this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }
}
