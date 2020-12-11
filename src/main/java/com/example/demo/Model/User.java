package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private @Email String email;
    private boolean loggedIn;

    public User() {
  ***REMOVED***

    public User(@NotBlank String username,
                @NotBlank String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
  ***REMOVED***

    public User(@NotBlank String username,
                @NotBlank String password,
                @NotBlank String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.loggedIn = false;
  ***REMOVED***

    public long getId() {
        return this.id;
  ***REMOVED***

    public String getUsername() {
        return this.username;
  ***REMOVED***

    public void setUsername(String username) {
        this.username = username;
  ***REMOVED***

    public String getPassword() {
        return this.password;
  ***REMOVED***

    public void setPassword(String password) {
        this.password = password;
  ***REMOVED***

    public String getEmail() {
        return email;
  ***REMOVED***

    public void setEmail(String email) {
        this.email = email;
  ***REMOVED***

    public boolean isLoggedIn() {
        return this.loggedIn;
  ***REMOVED***

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
  ***REMOVED***

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password);
  ***REMOVED***

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, loggedIn);
  ***REMOVED***

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", loggedIn=" + loggedIn +
                '***REMOVED***';
  ***REMOVED***
***REMOVED***
