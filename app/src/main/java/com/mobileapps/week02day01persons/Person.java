package com.mobileapps.week02day01persons;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable
{
    private String firstName;
    private String lastName;
    private String sex;
    private String age;

    Person(String firstName, String lastName, String sex, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

    public Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        sex = in.readString();
        age = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(sex);
        dest.writeString(age);
    }
}
