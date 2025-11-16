package com.sgagestudio.demo.document_reminder.data.factory;

import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;
import com.sgagestudio.demo.document_reminder.data.entity.UserRole;

import java.util.UUID;

public class UserFactory {

    public static class Builder {

        private final UserEntity instance;

        public Builder() {
            this.instance = new UserEntity();
        }

        public Builder setName(String name) {
            instance.setName(name);
            return this;
        }

        public Builder setEmail(String email) {
            instance.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            instance.setPassword(password);
            return this;
        }

        public Builder setOrganizationId(UUID organizationName) {
            instance.setOrganizationId(organizationName);
            return this;
        }

        public Builder setBusinessArea(String businessArea) {
            instance.setBusinessArea(businessArea);
            return this;
        }

        public Builder setRole(UserRole role) {
            instance.setRole(role);
            return this;
        }

        public UserEntity build() {
            return instance;
        }

    }

}
