package com.example.bank.DTO;

public class UserDTO {
    private String login;
    private String password;
    private String email;
    private String confirmPassword;
    private String answer;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private UserDTO(String login, String password, String email, String confirmPassword, String answer) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.confirmPassword = confirmPassword;
        this.answer = answer;
    }

    public static final class UserDTOBuilder {
        private String login;
        private String password;
        private String email;
        private String confirmPassword;
        private String answer;

        private UserDTOBuilder() {
        }

        public static UserDTOBuilder anUserDTO() {
            return new UserDTOBuilder();
        }

        public UserDTOBuilder withLogin(String login) {
            this.login = login;
            return this;
        }

        public UserDTOBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder withConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public UserDTOBuilder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(login, password, email, confirmPassword, answer);
        }
    }
}
