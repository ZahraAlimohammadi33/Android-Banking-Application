package com.example.myapplication;

public class Contact {

        private String firstName;
        private String lastName;
        private String phoneNumber;

        private int image;

        private int button;

        public Contact(String firstName, String lastName, String phoneNumber) {
            setFirstName(firstName);
            setLastName(lastName);
            setPhoneNumber(phoneNumber);
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getName() {
            return firstName + lastName ;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

    public int getButton() {
        return button;
    }

    public int getImage() {
        return image;
    }

    @Override
        public String toString() {
            return "firstName: " + firstName + " | " + "lastName: " + lastName + " | " + "phoneNumber: " + phoneNumber;
        }


}
